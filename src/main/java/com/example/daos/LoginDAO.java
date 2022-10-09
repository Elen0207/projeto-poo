package com.example.daos;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginDAO implements ILoginDAO {

//    private static final String DBURL = "jdbc:mariadb://localhost:3306/eletronicosdb";
//    private static final String DBUSER = "root";
//    private static final String DBPASS = "";
//
//    public LoginDAO (){
//        try{
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
    private static final String DBUSER = ""; //Colocar seu user
    private static final String DBPASS = ""; //Colocar sua senha

    public LoginDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] pesquisarPorNome(String nome, String login, String senha) {
        String[] log = new String[3];

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql = "SELECT * FROM TableUsuario where nomeUsuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            rs.next();

            log[0] = rs.getString("nomeUsuario");
            log[1] = (rs.getString("login"));
            log[2] = (rs.getString("senha"));

            con.close();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Favor verificar o Nome do Cliente digitado " + e.getMessage());
            alert.showAndWait();
            e.printStackTrace();

        }
        return log;
    }
}
