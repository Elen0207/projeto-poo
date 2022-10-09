package com.example.control;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

import com.example.daos.IProdutoDAO;
import com.example.daos.ProdutoDAO;
import com.example.entity.Produto;


public class ProdutoControl {
	    public IntegerProperty idProduto = new SimpleIntegerProperty(0);
	    public StringProperty nomeProduto = new SimpleStringProperty("");
	    public StringProperty descricaoProduto = new SimpleStringProperty("");
	    public DoubleProperty precoProduto = new SimpleDoubleProperty(0);
	    public IntegerProperty quantidadeDePecas = new SimpleIntegerProperty(0);

	    private ObservableList<Produto> listaView = FXCollections.observableArrayList();
	    private IProdutoDAO IprodutoDAO = new ProdutoDAO();

	    public Produto getEntity() {
	    	Produto p = new Produto();
	        p.setIdProduto(idProduto.get());
	        p.setNomeProduto(nomeProduto.get());
	        p.setDescricaoProduto(descricaoProduto.get());
	        p.setQuantidadeDePecas(quantidadeDePecas.get());
	        p.setPrecoProduto(precoProduto.get());
	       
	        return p;
	    }

	    public void setEntity(Produto p) {
	        idProduto.set(p.getIdProduto());
	        nomeProduto.set(p.getNomeProduto());
	        descricaoProduto.set(p.getDescricaoProduto());
			quantidadeDePecas.set(p.getQuantidadeDePecas());
	        precoProduto.set(p.getPrecoProduto());
	       
	    }

	    public void salvar() {
	    	Produto p = getEntity();
			boolean encontrado = false;

			for(int i = 0; i < listaView.size(); i++ ) {
				if (p.getIdProduto() == listaView.get(i).getIdProduto()) {
					encontrado = true;
				}
			}

			if (!encontrado) {
	        	IprodutoDAO.adicionar(p);
	        } else {
	        	IprodutoDAO.atualizar(p);
	        }
	        atualizarListaView();
			setEntity(new Produto());
	    }

	    public void pesquisar() {
	        listaView.clear();
	        List<Produto> encontrados = IprodutoDAO.pesquisarPorNome(nomeProduto.get());
	        listaView.addAll(encontrados);
	    }

	    public void remover(int idProduto) {
	           IprodutoDAO.remover(idProduto);
	           atualizarListaView();
	           
		}


	    public void atualizarListaView() {
	    	listaView.clear();
	    	listaView.addAll(IprodutoDAO.pesquisarPorNome(""));
	    }

	    public ObservableList<Produto> getListaView() {
	        return listaView;
	    }
	}
	
	


