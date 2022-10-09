package com.example.daos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import  com.example.entity.*;
import javafx.scene.control.Alert;

public class CarrinhoDAO implements ICarrinhoDAO {
    //    private static final String DBURL = "jdbc:mariadb://localhost:3306/eletronicosdb";
    //    private static final String DBUSER = "root";
    //    private static final String DBPASS = "";

    private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
    private static final String DBUSER = ""; //Colocar seu user
    private static final String DBPASS = ""; //Colocar sua senha

    public CarrinhoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public int VerificarCodigoCarrinho() {
        int codigoAtual = 0;
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT MAX(idGroupCarrinho) FROM GroupCarrinho";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            codigoAtual = rs.getInt("MAX(idGroupCarrinho)");

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n");
            alert.showAndWait();
        }
        return codigoAtual;
    }

    @Override
    public void AdicionarCarrinho(int idCarrinho, int idProduto, int codigoProduto, int qtde, double total) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO TableCarrinho (idCarrinho, idProduto, codigoCarrinho, quantidade, total)  " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCarrinho);
            stmt.setInt(2, idProduto);
            stmt.setInt(3, codigoProduto);
            stmt.setInt(4, qtde);
            stmt.setDouble(5, total);
            stmt.executeUpdate();

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void LimparCarrinho(int codigoCarrinho) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String sql = "DELETE FROM TableCarrinho WHERE codigoCarrinho = ? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigoCarrinho);
            stmt.executeUpdate();

            LimparGroupCarrinho(codigoCarrinho);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão ao Limpar Carrinho" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void LimparGroupCarrinho(int codigoCarrinho) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String deleteGroup = "DELETE FROM GroupCarrinho WHERE codigoCarrinho = ? ";
            PreparedStatement stmtGroup = con.prepareStatement(deleteGroup);
            stmtGroup.setInt(1, codigoCarrinho);
            stmtGroup.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão ao Limpar Grupo de Carrinhos" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void RemoverProduto(int idCarrinho, int idProduto, int qtd) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String sql = "DELETE FROM TableCarrinho WHERE idCarrinho = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, idCarrinho);
            stmt.executeUpdate();

            SelectProduto(idProduto, qtd);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro ao remover o Produto" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void SelectProduto(int idProduto, int qtd) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String selectProduto = "SELECT * FROM TableProdutos where idProduto = ?";
            PreparedStatement stmtSelectProduto = con.prepareStatement(selectProduto);
            stmtSelectProduto.setInt(1, idProduto);
            ResultSet rsSelectProduto = stmtSelectProduto.executeQuery();
            rsSelectProduto.next();
            int qtdeContida = rsSelectProduto.getInt("quantidadeDePecas");
            int qtdeNova = qtdeContida + qtd;

            AtualizarQtdeProduto(idProduto, qtdeNova);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro ao selecionar o Produto" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    private void AtualizarQtdeProduto(int idProduto, int qtdeNova) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String updateProduto = "UPDATE TableProdutos SET quantidadeDePecas = "
                    + qtdeNova +" WHERE idProduto = " + idProduto +"";
            PreparedStatement stmtUpdateProduto = con.prepareStatement(updateProduto);
            stmtUpdateProduto.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro ao atualizar a quantidade de Produto" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void Atualizar(Carrinho c) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String updateCarrinho = "UPDATE TableCarrinho SET quantidade = "
                + c.getQtdeItem() + ", total ="+ c.getTotal() +
                    " WHERE idProduto = " + c.getIdProduto() +"";
            PreparedStatement stmt = con.prepareStatement(updateCarrinho);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void SalvarCarrinho(int id, int codigoCarrinho) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO GroupCarrinho (idGroupCarrinho, codigoCarrinho) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, codigoCarrinho);

            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void UptadeProduto(int id, int qtde) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            int qtdeProdutos = Integer.parseInt(VerificarProduto(id)[1]) - qtde;
            String updateProduto = "UPDATE TableProdutos SET quantidadeDePecas = "
                    + qtdeProdutos +" WHERE idProduto = " + id +"";
            PreparedStatement stmt = con.prepareStatement(updateProduto);
            stmt.executeUpdate();
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public List<Carrinho> Listar(int codigoCarrinho) {
        List<Carrinho> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM TableCarrinho WHERE codigoCarrinho like '%"+ codigoCarrinho +"%'";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Carrinho carrinho = new Carrinho();

                carrinho.setIdCarrinho( rs.getInt("idCarrinho") );
                carrinho.setIdProduto( rs.getInt("idProduto") );
                carrinho.setCodigoCarrinho( rs.getInt("codigoCarrinho") );
                carrinho.setQtdeItem( rs.getInt("codigoCarrinho") );

                encontrados.add(carrinho);
            }
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
        return encontrados;
    }

    @Override
    public String[] VerificarProduto(int id) {
        String [] Produto = new String[4];
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM TableProdutos where idProduto = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            Produto[0] = rs.getString("nomeDoProduto");
            Produto[1] = String.valueOf(rs.getInt("quantidadeDePecas"));
            Produto[2] = String.valueOf(rs.getFloat("precoUnitario"));

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados" + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }

        return Produto;
    }
}
