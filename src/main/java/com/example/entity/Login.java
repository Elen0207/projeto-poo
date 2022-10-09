package com.example.entity;

public class Login {

    String nomeAcesso;
    String loginAcesso;
    String senhaAcesso;
    String mensagemAcesso;


    public String getNomeAcesso() {
        return nomeAcesso;
    }

    public void setNomeAcesso(String nomeAcesso) {
        this.nomeAcesso = nomeAcesso;
    }

    public String getLoginAcesso() {
        return loginAcesso;
    }

    public void setLoginAcesso(String loginAcesso) {
        this.loginAcesso = loginAcesso;
    }

    public String getSenhaAcesso() {
        return senhaAcesso;
    }

    public void setSenhaAcesso(String senhaAcesso) {
        this.senhaAcesso = senhaAcesso;
    }

    public String getMensagemAcesso() { return mensagemAcesso;   }

    public void setMensagemAcesso(String mensagemAcesso) { this.mensagemAcesso = mensagemAcesso; }
}
