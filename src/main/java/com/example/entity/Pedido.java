package com.example.entity;

import java.time.LocalDate;

public class Pedido {

  private int idPedido;
  private String idCliente;
  private String idFuncionario;
  private int idCarrinho;
  private LocalDate dataDoPedido;
  private LocalDate previsaoDeEntrega;
  private String codigoTransportadora;
  private double custoFrete;
  private String formaPagamento;

  public String getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(String idCliente) {
    this.idCliente = idCliente;
  }

  public String getIdFuncionario() {
    return idFuncionario;
  }

  public void setIdFuncionario(String idFuncionario) {
    this.idFuncionario = idFuncionario;
  }

  public Double getTotalDoPedido() {
    return totalDoPedido;
  }

  public void setTotalDoPedido(Double totalDoPedido) {
    this.totalDoPedido = totalDoPedido;
  }

  private Double totalDoPedido;

  public int getIdPedido() {
    return idPedido;
  }

  public void setIdPedido(int idPedido) {
    this.idPedido = idPedido;
  }

  public int getIdCarrinho() {
    return idCarrinho;
  }

  public void setIdCarrinho(int idCarrinho) {
    this.idCarrinho = idCarrinho;
  }

  public String getCodigoTransportadora() {
    return codigoTransportadora;
  }

  public void setCodigoTransportadora(String codigoTransportadora) {
    this.codigoTransportadora = codigoTransportadora;
  }

  public double getCustoFrete() {
    return custoFrete;
  }

  public void setCustoFrete(double custoFrete) {
    this.custoFrete = custoFrete;
  }

  public String getFormaPagamento() {
    return formaPagamento;
  }

  public void setFormaPagamento(String formaPagamento) {
    this.formaPagamento = formaPagamento;
  }

  public LocalDate getDataDoPedido() {
    return dataDoPedido;
  }

  public void setDataDoPedido(LocalDate dataDoPedido) {
    this.dataDoPedido = dataDoPedido;
  }

  public LocalDate getPrevisaoDeEntrega() {
    return previsaoDeEntrega;
  }

  public void setPrevisaoDeEntrega(LocalDate previsaoDeEntrega) {
    this.previsaoDeEntrega = previsaoDeEntrega;
  }

}