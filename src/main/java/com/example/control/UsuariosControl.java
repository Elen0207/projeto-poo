package com.example.control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.entity.Usuarios;
import com.example.daos.*;
import java.util.List;


public class UsuariosControl {
    public IntegerProperty idUsuario = new SimpleIntegerProperty(0);
    public StringProperty nomeUsuario = new SimpleStringProperty("");
    public IntegerProperty CEP = new SimpleIntegerProperty(0);
    public StringProperty ruaEnderecoNumero = new SimpleStringProperty("");
    public StringProperty cidadeEndereco = new SimpleStringProperty("");
    public StringProperty estadoEndereco = new SimpleStringProperty("");
    public IntegerProperty telefoneUsuario = new SimpleIntegerProperty(0);
    public StringProperty tipoUsuario = new SimpleStringProperty("");
    public StringProperty login = new SimpleStringProperty("");
    public StringProperty senha = new SimpleStringProperty("");

    private ObservableList<Usuarios> listaView = FXCollections.observableArrayList();
    private IUsuarioDAO usuarioDAO = new UsuariosDAO();

    public Usuarios getEntity() {
        Usuarios u = new Usuarios();
        u.setIdUsuario(idUsuario.get());
        u.setNomeUsuario(nomeUsuario.get());
        u.setCEP(CEP.get());
        u.setRuaEnderecoNumero(ruaEnderecoNumero.get());
        u.setCidadeEndereco(cidadeEndereco.get());
        u.setEstadoEndereco(estadoEndereco.get());
        u.setTelefoneUsuario(telefoneUsuario.get());
        u.setTipoUsuario(tipoUsuario.get());
        u.setLogin(login.get());
        u.setSenha(senha.get());
        return u;
    }
    public void setEntity(Usuarios u){
        idUsuario.set(u.getIdUsuario());
        nomeUsuario.set(u.getNomeUsuario());
        CEP.set(u.getCEP());
        ruaEnderecoNumero.set(u.getRuaEnderecoNumero());
        cidadeEndereco.set(u.getCidadeEndereco());
        estadoEndereco.set(u.getEstadoEndereco());
        telefoneUsuario.set(u.getTelefoneUsuario());
        tipoUsuario.set(u.getTipoUsuario());
        login.set(u.getLogin());
        senha.set(u.getSenha());
    }

    public void salvar() {
        Usuarios u = getEntity();
        usuarioDAO.adicionar(u);
        atualizarListaView();
        setEntity(new Usuarios());
    }

    public void pesquisar() {
        listaView.clear();
        List<Usuarios> encontrados = usuarioDAO.pesquisarPorNome(nomeUsuario.get());
        listaView.addAll(encontrados);
    }

    public void remover(long id) {
        usuarioDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarU() {
        Usuarios u = getEntity();
        usuarioDAO.atualizar(u);
        atualizarListaView();
        setEntity(new Usuarios());
    }

    public void atualizarListaView() {
        listaView.clear();
        listaView.addAll(usuarioDAO.pesquisarPorNome(""));
    }

    public ObservableList<Usuarios> getListaView() {
        return listaView;
    }
} // fim da classe

