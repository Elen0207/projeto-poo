package com.example.daos;


import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.example.entity.Usuarios;


public class UsuariosDAO implements IUsuarioDAO {

//    private static final String DBURL = "jdbc:mariadb://localhost:3306/eletronicosdb";
//    private static final String DBUSER = "root";
//    private static final String DBPASS = "";
//
//    public UsuariosDAO() {
//        try{
//            Class.forName("org.mariadb.jdbc.Driver");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
    private static final String DBUSER = ""; //Colocar seu user
    private static final String DBPASS = ""; //Colocar sua senha

    public UsuariosDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adicionar(Usuarios u) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "INSERT INTO TableUsuario ( idUsuario, nomeUsuario, CEP, ruaEnderecoNumero, cidadeEndereco, " +
                    "estadoEndereco, telefoneUsuario, tipoUsuario, login, senha) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, u.getIdUsuario());
            stmt.setString(2, u.getNomeUsuario());
            stmt.setInt(3,  u.getCEP());
            stmt.setString(4, u.getRuaEnderecoNumero());
            stmt.setString(5, u.getCidadeEndereco());
            stmt.setString(6, u.getEstadoEndereco());
            stmt.setInt(7, u.getTelefoneUsuario());
            stmt.setString(8, u.getTipoUsuario());
            stmt.setString(9, u.getLogin());
            stmt.setString(10, u.getSenha());

            stmt.executeUpdate();

            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuarios> pesquisarPorNome(String nome) {
        List<Usuarios> encontrados = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

            String sql = "SELECT * FROM TableUsuario WHERE nomeUsuario like '%" + nome + "%'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Usuarios usuarios = new Usuarios();
                usuarios.setIdUsuario( rs.getInt("idUsuario") );
                usuarios.setNomeUsuario( rs.getString("nomeUsuario") );
                usuarios.setCEP( rs.getInt("CEP") );
                usuarios.setRuaEnderecoNumero( rs.getString("ruaEnderecoNumero") );
                usuarios.setCidadeEndereco( rs.getString("cidadeEndereco") );
                usuarios.setEstadoEndereco( rs.getString("estadoEndereco") );
                usuarios.setTelefoneUsuario( rs.getInt("telefoneUsuario") );
                usuarios.setTipoUsuario( rs.getString("tipoUsuario") );
                usuarios.setLogin( rs.getString("login") );
                usuarios.setSenha( rs.getString("senha") );

                encontrados.add(usuarios);
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

    @Override
    public void atualizar(Usuarios u) {

        try {
            Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            String sql1 = "UPDATE TableUsuario SET nomeUsuario = ?, CEP = ?, ruaEnderecoNumero = ?, cidadeEndereco = ?, estadoEndereco = ?, telefoneUsuario = ?, tipoUsuario = ?, login = ?, senha = ?  WHERE idUsuario = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);

            stmt.setInt(1, u.getIdUsuario());
            stmt.setString(2, u.getNomeUsuario());
            stmt.setInt(3,  u.getCEP());
            stmt.setString(4, u.getRuaEnderecoNumero());
            stmt.setString(5, u.getCidadeEndereco());
            stmt.setString(6, u.getEstadoEndereco());
            stmt.setInt(7, u.getTelefoneUsuario());
            stmt.setString(8, u.getTipoUsuario());
            stmt.setString(9, u.getLogin());
            stmt.setString(10, u.getSenha());

            stmt.executeUpdate();


            con.close();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }

    }

    @Override
    public void remover(long id) {
        try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
            String sql = "DELETE FROM TableUsuario WHERE idusuario = ?";
            System.out.println(sql);
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Ocorreu um erro na conexão com o Banco de Dados\n" + e);
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
