package com.example.control;

import com.example.daos.ILoginDAO;
import com.example.daos.LoginDAO;
import com.example.entity.Login;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class LoginControl {

    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty login = new SimpleStringProperty("");
    public StringProperty senha = new SimpleStringProperty("");
    public StringProperty mensagen = new SimpleStringProperty("");

    private List<Login> lista = new ArrayList<>();
    private ILoginDAO loginDAO = new LoginDAO();

    public Login getEntity(){
        Login l = new Login();
        l.setNomeAcesso(nome.get());
        l.setLoginAcesso(login.get());
        l.setSenhaAcesso(senha.get());
        l.setMensagemAcesso(mensagen.get());

        return l;
    }

    public void setEntity(String mensagem) {
        mensagen.set(mensagem);

    }

    public String[] validar() {
        Login l = new Login();
        l.setNomeAcesso(nome.get());
        l.setLoginAcesso(login.get());
        l.setSenhaAcesso(senha.get());

        String[] log = new String[] {l.getNomeAcesso(), l.getLoginAcesso(), l.getSenhaAcesso()};
        String[] nomes = loginDAO.pesquisarPorNome(l.getNomeAcesso(), l.getLoginAcesso(), l.getSenhaAcesso());


        if (nomes[1].equals(log[1]) & (nomes[2].equals(log[2]))) {
            setEntity("ACESSO LIBERADO");

        } else {
            setEntity("ACESSO NEGADO - TENTE NOVAMENTE");
        }

        return null;
    }

    public String esqueciSenha() {
        System.out.println("O Link de reset de senha foi enviado para o e-mail cadastrado");
        return null;
    }
}