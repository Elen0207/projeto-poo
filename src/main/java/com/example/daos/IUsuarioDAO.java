package com.example.daos;

import com.example.entity.Usuarios;
import java.util.List;

public interface IUsuarioDAO {
    void adicionar(Usuarios u);
    List<Usuarios> pesquisarPorNome(String nome);
    void atualizar(Usuarios u);
    void remover(long id);
}
