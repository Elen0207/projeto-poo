package com.example.control;


import com.example.daos.ClienteDAO;
import com.example.daos.IClienteDAO;
import com.example.entity.Cliente;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;


public class ClienteControl {

    public IntegerProperty idCiente = new SimpleIntegerProperty(0);
    public StringProperty nomeCliente = new SimpleStringProperty("");
    public LongProperty CPF = new SimpleLongProperty(0);
    public StringProperty bandeiraCartao = new SimpleStringProperty("");
    public LongProperty numeroCartao = new SimpleLongProperty(0);
    public StringProperty nomeNoCartao = new SimpleStringProperty("");
    public StringProperty validade = new SimpleStringProperty("");
    public StringProperty seguranca = new SimpleStringProperty("");


    private ObservableList<Cliente> listaViewC = FXCollections.observableArrayList();
    private IClienteDAO clienteDAO  = new ClienteDAO();

    public Cliente getEntity() {
        Cliente c = new Cliente();
        c.setIdCliente(idCiente.get());
        c.setNomeCliente(nomeCliente.get());
        c.setCPF(CPF.get());
        c.setBandeiraCartao(bandeiraCartao.get());
        c.setNumeroCartao(numeroCartao.get());
        c.setNomeNoCartao(nomeNoCartao.get());
        c.setValidade(validade.get());
        c.setSeguranca(seguranca.get());

        return c;
    }
    public void setEntity(Cliente c ){
        idCiente.set(c.getIdCliente());
        nomeCliente.set(c.getNomeCliente());
        CPF.set(c.getCPF());
        bandeiraCartao.set(c.getBandeiraCartao());
        numeroCartao.set(c.getNumeroCartao());
        nomeNoCartao.set(c.getNomeNoCartao());
        validade.set(c.getValidade());
        seguranca.set(c.getSeguranca());
    }

    public void salvar() {
        Cliente c = getEntity();
        clienteDAO.adicionar(c);
        atualizarListaView();
        setEntity(new Cliente());
    }

    public void pesquisarCliente() {
        listaViewC.clear();
        List<Cliente> encontrados = clienteDAO.pesquisarPorCliente(nomeCliente.get());
        listaViewC.addAll(encontrados);
    }

    public void remover(int idCliente) {
        clienteDAO.removerCliente(idCliente);
        atualizarListaView();
    }

    public void atualizar() {
        Cliente c = getEntity();
        clienteDAO.atualizarCliente(c.getIdCliente(), c.getNomeCliente(), c.getCPF(), c.getBandeiraCartao(),c.getNumeroCartao(), c.getNomeNoCartao(),c.getValidade(),c.getSeguranca());
        atualizarListaView();
        setEntity(new Cliente());
    }

    public void atualizarListaView() {
        listaViewC.clear();
        listaViewC.addAll(clienteDAO.pesquisarPorCliente(""));
    }

    public ObservableList<Cliente> getListaView() {
        return listaViewC;
    }
}

