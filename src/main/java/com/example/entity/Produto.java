package com.example.entity;

public class Produto {
	
	private int idProduto;
	private String nomeProduto;
	private double precoProduto;
	private String descricaoProduto;
	private int quantidadeDePecas;

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public String getDescricaoProduto() {
		return descricaoProduto;
	}

	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}

	public int getQuantidadeDePecas() {
		return quantidadeDePecas;
	}

	public void setQuantidadeDePecas(int quantidadeDePecas) {
		this.quantidadeDePecas = quantidadeDePecas;
	}
}



