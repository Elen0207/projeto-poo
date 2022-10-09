package com.example.daos;

import com.example.entity.Pedido;
import java.util.List;

public interface IPedidoDAO {
    List<String> localizarFuncionario();
    List<String> localizarCliente();
    void SalvarPedido(Pedido pedido);
    double calcularPrecoTotal(int codigoCarrinho);
}
