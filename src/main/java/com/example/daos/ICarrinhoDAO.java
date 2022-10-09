package com.example.daos;

import com.example.entity.Carrinho;
import java.util.List;

public interface ICarrinhoDAO {
    int VerificarCodigoCarrinho();
    void AdicionarCarrinho(int idCarrinho, int idProduto, int codigoProduto, int qtde, double total);
    List<Carrinho> Listar(int codigoCarrinho);
    void LimparCarrinho(int codigoCarrinho);
    void  Atualizar(Carrinho carrinho);
    void RemoverProduto(int idCarrinho, int idProduto, int qtd);
    String [] VerificarProduto(int idProduto);
    void SalvarCarrinho(int id, int codigoCarrinho);
    void UptadeProduto(int id, int qtde);
}
