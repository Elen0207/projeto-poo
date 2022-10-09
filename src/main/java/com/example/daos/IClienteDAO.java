package com.example.daos;

import java.util.List;
import com.example.entity.Cliente;

public interface IClienteDAO {
    void adicionar(Cliente c);
   List<Cliente> pesquisarPorCliente(String nome);
    void removerCliente(int id);
    void atualizarCliente(int idCliente, String NomeCliente, long cpf, String bandeiraCartao, long numeroCartao, String nomeNoCartao, String validade, String seguranca);
}
