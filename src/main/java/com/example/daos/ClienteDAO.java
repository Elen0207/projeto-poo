package com.example.daos;



import com.example.entity.Cliente;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAO implements IClienteDAO {
//    private static final String DBURL = "jdbc:mariadb://localhost:3306/eletronicosdb";
//    private static final String DBUSER = "root";
//    private static final String DBPASS = "";
//
    private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
    private static final String DBUSER = ""; //Colocar seu user
    private static final String DBPASS = ""; //Colocar sua senha

    public ClienteDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void adicionar(Cliente c) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "INSERT INTO TableCliente ( idCliente, nomeCliente, CPF, bandeiraCartao, numeroCartao, " +
                    "nomeNoCartao, validade, seguranca ) VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, c.getIdCliente());
            stmt.setString(2, c.getNomeCliente());
            stmt.setLong(3, c.getCPF());
            stmt.setString(4, c.getBandeiraCartao());
            stmt.setLong(5, c.getNumeroCartao());
            stmt.setString(6, c.getNomeNoCartao());
            stmt.setString(7, c.getValidade());
            stmt.setString(8, c.getSeguranca());

            stmt.executeUpdate();
            System.out.println("Teste");

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public List<Cliente> pesquisarPorCliente(String nome) {
        List<Cliente> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "SELECT * FROM TableCliente WHERE nomeCliente like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente( rs.getInt("idCliente") );
                cliente.setNomeCliente( rs.getString("nomeCliente") );
                cliente.setCPF( rs.getLong("CPF") );
                cliente.setBandeiraCartao( rs.getString("bandeiraCartao") );
                cliente.setNumeroCartao( rs.getLong("numeroCartao") );
                cliente.setNomeNoCartao( rs.getString("nomeNoCartao"));
                cliente.setValidade( rs.getString("validade") );
                cliente.setSeguranca( rs.getString("seguranca") );

                encontrados.add(cliente);
            }
            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
        return encontrados;
    }

    // ajustando



    @Override
    public void removerCliente(int idCliente) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String sql = "DELETE FROM TableCliente WHERE idCliente = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            stmt.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public void atualizarCliente(int idCliente, String NomeCliente, long cpf, String bandeiraCartao, long numeroCartao, String nomeNoCartao, String validade, String seguranca) {
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql1 = "UPDATE TableCliente SET nomeCliente = ?, CPF = ?, bandeiraCartao = ?, numeroCartao = ?, nomeNoCartao = ?, validade = ?, seguranca = ? WHERE idCliente = ?";

            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(8, idCliente);
            stmt.setString(1,NomeCliente);
            stmt.setLong(2,  cpf);
            stmt.setString(3, bandeiraCartao);
            stmt.setLong(4, numeroCartao);
            stmt.setString(5, nomeNoCartao);
            stmt.setString(6, validade);
            stmt.setString(7, seguranca);

            stmt.executeUpdate();

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
