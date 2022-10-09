package com.example.control;

import com.example.daos.CarrinhoDAO;
import com.example.daos.ICarrinhoDAO;
import com.example.entity.Carrinho;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class CarrinhoControl {
    public IntegerProperty idCarrinho = new SimpleIntegerProperty(0);
    public IntegerProperty idProduto = new SimpleIntegerProperty(0);
    public IntegerProperty codigoCarrinho = new SimpleIntegerProperty(0);
    public IntegerProperty qtdeItem = new SimpleIntegerProperty(0);
    public StringProperty nomeProduto = new SimpleStringProperty("");
    public DoubleProperty valorProduto = new SimpleDoubleProperty(0);
    public DoubleProperty total = new SimpleDoubleProperty(0);

    private ObservableList<Carrinho> listaView = FXCollections.observableArrayList();
    private ICarrinhoDAO carrinhoDAO = new CarrinhoDAO();

    public Carrinho getEntity() {
        Carrinho c = new Carrinho();

        c.setIdCarrinho(idCarrinho.get());
        c.setIdProduto(idProduto.get());
        c.setCodigoCarrinho(verificarCodigoCarrinho());
        c.setQtdeItem(qtdeItem.get());
        c.setNomeProduto(nomeProduto.get());
        c.setValorProduto(valorProduto.get());
        c.setTotal(total.get());

        return c;
    }

    public void setEntity(Carrinho c) {
        idCarrinho.set(c.getIdCarrinho());
        idProduto.set(c.getIdProduto());
        codigoCarrinho.set(verificarCodigoCarrinho());
        qtdeItem.set(c.getQtdeItem());
        nomeProduto.set(c.getNomeProduto());
        valorProduto.set(c.getValorProduto());
        total.set(c.getTotal());
    }

    public int verificarCodigoCarrinho() {
        int codigo = carrinhoDAO.VerificarCodigoCarrinho();
        return codigo;
    }

    public String [] verificarProduto(int idProduto) {
        String [] Produto = carrinhoDAO.VerificarProduto(idProduto);
        return Produto;
    }

    public void adicionarCarrinho() {
        Carrinho carrinho = getEntity();
        boolean encontrado = false;
        for(int i = 0; i < listaView.size(); i++ ) {
            if (carrinho.getIdProduto() == listaView.get(i).getIdProduto()) {
                encontrado = true;
            }
        }

        if (!encontrado) {
            carrinhoDAO.AdicionarCarrinho(
                    carrinho.getIdCarrinho(),
                    carrinho.getIdProduto(),
                    carrinho.getCodigoCarrinho(),
                    carrinho.getQtdeItem(),
                    carrinho.getTotal()
            );

            listaView.add(carrinho);
            carrinhoDAO.UptadeProduto(carrinho.getIdProduto(), carrinho.getQtdeItem());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,
                    "Você confirma a alteração do produto " +
                            carrinho.getNomeProduto(), ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> clicado = alert.showAndWait();
            if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                carrinhoDAO.Atualizar(carrinho);
                for (Carrinho c : listaView) {
                    if (c.getIdProduto() == carrinho.getIdProduto()) {
                        listaView.remove(c);
                        break;
                    }
                }

                listaView.add(carrinho);
            }
        }

        getListaView();
        setEntity(new Carrinho());
    }

    public void salvarCarrinho(int codigoCarrinho) {
        int id = verificarCodigoCarrinho() + 1;
        carrinhoDAO.SalvarCarrinho(id, codigoCarrinho);
    }

    public void limparCarrinho() {
        carrinhoDAO.LimparCarrinho(verificarCodigoCarrinho());
        atualizarListaView();
    }

    public void remover(int idCarrinho, int idProduto, int qtd) {
        for (Carrinho carrinho : listaView) {
            if (carrinho.getIdProduto() == idProduto) {
                listaView.remove(carrinho);
                break;
            }
        }
       carrinhoDAO.RemoverProduto(idCarrinho, idProduto, qtd);
        getListaView();
    }

    public void atualizarListaView() {
        listaView.clear();
        listaView.addAll();
    }

    public ObservableList<Carrinho> getListaView() {
        return listaView;
    }
}
