package com.example.daos;

import java.util.List;
import com.example.entity.Produto;

public interface IProdutoDAO {
  void adicionar(Produto p);
  List<Produto> pesquisarPorNome(String nome);
  void atualizar(Produto p);
  void remover(int id);

}
