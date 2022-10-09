package com.example.control;

import com.example.daos.IPedidoDAO;
import com.example.daos.PedidoDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import com.example.entity.Pedido;


public class PedidoControl {

    public IntegerProperty idPedido = new SimpleIntegerProperty(0);
    public StringProperty idCliente = new SimpleStringProperty("");
    public StringProperty idFuncionario = new SimpleStringProperty("");
    public IntegerProperty idCarrinho = new SimpleIntegerProperty(0);
    public ObjectProperty dataDoPedido = new SimpleObjectProperty(LocalDate.now());
    public ObjectProperty previsaoDeEntrega = new SimpleObjectProperty(LocalDate.now());
    public DoubleProperty custoFrete = new SimpleDoubleProperty(0.0);
    public StringProperty formaPagamento = new SimpleStringProperty("");
    public StringProperty codigoTransportadora = new SimpleStringProperty("");
    public DoubleProperty totalDoPedido = new SimpleDoubleProperty(0.0);


    private ObservableList<Pedido> listaView = FXCollections.observableArrayList();
    private IPedidoDAO pedidoDAO = new PedidoDAO();
    private CarrinhoControl carrinhoControl = new CarrinhoControl();

    public Pedido getEntity() {
        Pedido p = new Pedido();

        p.setIdPedido(idPedido.get());
        p.setIdCarrinho(idCarrinho.get());
        p.setIdCliente(idCliente.get());
        p.setIdFuncionario(idFuncionario.get());
        p.setDataDoPedido((LocalDate) dataDoPedido.get());
        p.setPrevisaoDeEntrega((LocalDate) previsaoDeEntrega.get());
        p.setCustoFrete(custoFrete.get());
        p.setFormaPagamento(formaPagamento.get());
        p.setCodigoTransportadora(codigoTransportadora.get());
        p.setTotalDoPedido(totalDoPedido.get());

        return p;
    }

    public void setEntity(Pedido p) {
        idPedido.set(p.getIdPedido());
        idCarrinho.set(p.getIdCarrinho());
        idCliente.set(p.getIdCliente());
        idFuncionario.set(p.getIdFuncionario());
        dataDoPedido.set(p.getDataDoPedido());
        previsaoDeEntrega.set(p.getPrevisaoDeEntrega());
        custoFrete.set(p.getCustoFrete());
        formaPagamento.set(p.getFormaPagamento());
        codigoTransportadora.set(p.getCodigoTransportadora());
        totalDoPedido.set(p.getTotalDoPedido());
    }

    public void salvarPedido() {
        Pedido pedido = getEntity();
        pedidoDAO.SalvarPedido(pedido);
    }

    public List<String> localizarCliente(){
        List<String> listCliente = pedidoDAO.localizarCliente();
        return listCliente;
    }

    public List<String> localizarFuncionario(){
        List<String> listFuncionario = pedidoDAO.localizarFuncionario();
        return listFuncionario;
    }



    public double calcularPrecoTotal(int codigoCarrinho) {
        double precoTotal = pedidoDAO.calcularPrecoTotal(codigoCarrinho);
        return precoTotal;
    }

    public ObservableList<Pedido> getListaView() {

        return listaView;
    }
}
