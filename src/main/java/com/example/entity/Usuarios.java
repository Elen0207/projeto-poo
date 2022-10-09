package com.example.entity;

public class Usuarios {

    private int idUsuario;
    private String nomeUsuario;
    private int CEP;
    private String ruaEnderecoNumero;
    private String cidadeEndereco;
    private String estadoEndereco;
    private int telefoneUsuario;
    private String tipoUsuario;     // cliente, funcionario
    private String login;

    public void setSenha(String senha) {
        Senha = senha;
    }

    private String Senha;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public String getRuaEnderecoNumero() {
        return ruaEnderecoNumero;
    }

    public void setRuaEnderecoNumero(String ruaEnderecoNumero) {
        this.ruaEnderecoNumero = ruaEnderecoNumero;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getEstadoEndereco() {
        return estadoEndereco;
    }

    public void setEstadoEndereco(String estadoEndereco) {
        this.estadoEndereco = estadoEndereco;
    }

    public int getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(int telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return Senha;
    }
}
