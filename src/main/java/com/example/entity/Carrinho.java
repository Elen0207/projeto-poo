package com.example.entity;

public class Carrinho extends Produto {
  private int idCarrinho;
  private int idProduto;
  private int codigoCarrinho;
  private int qtdeItem;
  private String nomeProduto;
  private Double valorProduto;
  private Double total;

  @Override
  public String getNomeProduto() {
    return nomeProduto;
  }

  @Override
  public void setNomeProduto(String nomeProduto) {
    this.nomeProduto = nomeProduto;
  }

  public Double getValorProduto() {
    return valorProduto;
  }

  public void setValorProduto(Double valorProduto) {
    this.valorProduto = valorProduto;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public int getIdCarrinho() {
    return idCarrinho;
  }

  public void setIdCarrinho(int idCarrinho) {
    this.idCarrinho = idCarrinho;
  }

  public int getIdProduto() {
    return idProduto;
  }

  public void setIdProduto(int idProduto) {
    this.idProduto = idProduto;
  }

  public int getCodigoCarrinho() {
    return codigoCarrinho;
  }

  public void setCodigoCarrinho(int codigoCarrinho) {
    this.codigoCarrinho = codigoCarrinho;
  }

  public int getQtdeItem() {
    return qtdeItem;
  }

  public void setQtdeItem(int qtdeItem) {
    this.qtdeItem = qtdeItem;
  }
}