package com.example.daos;

import com.example.entity.Pedido;
import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO implements  IPedidoDAO {
    //    private static final String DBURL = "jdbc:mariadb://localhost:3306/eletronicosdb";
    //    private static final String DBUSER = "root";
    //    private static final String DBPASS = "";

    private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
    private static final String DBUSER = ""; //Colocar seu user
    private static final String DBPASS = ""; //Colocar sua senha

    public PedidoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> localizarFuncionario() {
        List<String> listFuncionario = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM TableUsuario WHERE tipoUsuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "f");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                listFuncionario.add(rs.getString("nomeUsuario"));
            }
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
        return listFuncionario;
    }

    @Override
    public List<String> localizarCliente() {
        List<String> listCliente = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM TableUsuario WHERE tipoUsuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "c");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                listCliente.add(rs.getString("nomeUsuario"));
            }
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }

        return listCliente;
    }

    @Override
    public double calcularPrecoTotal(int codigoCarrinho) {
        double precoTotal = 0.0;
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT total FROM TableCarrinho WHERE codigoCarrinho = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, codigoCarrinho);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                precoTotal = precoTotal + rs.getDouble("total");
            }
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
        return precoTotal;
    }

    @Override
    public void SalvarPedido(Pedido pedido) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "INSERT INTO TablePedidos (idPedido, idCliente, idFuncionario, codigoCarrinho, " +
                    "previsaoDeEntrega, formaDePagamento, codigoDaTransportadora, frete)  " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdPedido());
            stmt.setString(2, pedido.getIdCliente());
            stmt.setString(3, pedido.getIdFuncionario());
            stmt.setInt(4, pedido.getIdPedido());
            stmt.setDate(5, java.sql.Date.valueOf(String.valueOf(pedido.getPrevisaoDeEntrega())));
            stmt.setString(6, pedido.getFormaPagamento());
            stmt.setString(7, pedido.getCodigoTransportadora());
            stmt.setDouble(8, pedido.getCustoFrete());
            stmt.executeUpdate();

            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Pedido realizado com sucesso");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }

}
