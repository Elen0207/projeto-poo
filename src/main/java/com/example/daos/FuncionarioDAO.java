package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.Funcionario;
import javafx.scene.control.Alert;

public class FuncionarioDAO implements IFuncionarioDAO{
//
//	private static final String DBURL="jdbc:mariadb://localhost:3306/EletronicosDB";
//	private static final String DBUSER="root";
//	private static final String DBPASS ="";
//
//
//	public FuncionarioDAO() {
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//
//		} catch (Exception e) {
//			e.printStackTrace();}
//	}

	private static final String DBURL = "jdbc:mysql://localhost:3306/EletronicosDB"; //Colocar sua conexão
	private static final String DBUSER = ""; //Colocar seu user
	private static final String DBPASS = ""; //Colocar sua senha

	public FuncionarioDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Colocar sua conexão
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adicionar(Funcionario f) {

		try {
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);

			String sql ="INSERT INTO TableFuncionario (idFuncionario, cargoFuncionario, setor)  "
					+ "VALUES (?, ?, ? )";

			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, f.getIdFuncionario());
			stmt.setString(2, f.getCargoFuncionario());
			stmt.setString(3, f.getSetor());

			stmt.executeUpdate();

		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Ocorreu um erro na conexão com o Banco de Dados\n" + e);
			alert.showAndWait();
			e.printStackTrace();
		}

	}



	@Override
	public List<Funcionario> pesquisarPorCargo(String cargo) {
		List<Funcionario> encontrados = new ArrayList<>();
		try {
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			String sql = "SELECT * FROM TableFuncionario WHERE cargoFuncionario like '%" + cargo + "%'";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				Funcionario f = new Funcionario();

				f.setIdFuncionario( rs.getInt("idFuncionario") );
				f.setCargoFuncionario(rs.getString("cargoFuncionario"));
				f.setSetor( rs.getString("setor") );


				encontrados.add(f);
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
	public void atualizar(Funcionario f) {
		try {
			Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			String sql = "UPDATE TableFuncionario SET idFuncionario = ?, cargoFuncionario = ?, setor = ? WHERE idFuncionario = ?";
			System.out.println(sql);
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, f.getIdFuncionario());
			stmt.setString(2, f.getCargoFuncionario());
			stmt.setString(3, f.getSetor());
			stmt.setInt(4, f.getIdFuncionario());

			stmt.executeUpdate();
			con.close();
		} catch (SQLException  e) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Ocorreu um erro na conexão com o Banco de Dados\n" + e);
			alert.showAndWait();
			e.printStackTrace();
		}
	}


	@Override
	public void remover(int id) {
		try (Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS)) {
			String sql = "DELETE FROM TableFuncionario WHERE idFuncionario = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.WARNING,
					"Ocorreu um erro na conexão com o Banco de Dados\n" + e);
			alert.showAndWait();
			e.printStackTrace();
		}
	}
}