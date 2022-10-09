package com.example.daos;

import java.util.List;
import com.example.entity.Funcionario;

public interface IFuncionarioDAO {
	void adicionar(Funcionario f);
	List<Funcionario> pesquisarPorCargo(String cargo);
	void atualizar(Funcionario p);
	void remover(int id);
}
