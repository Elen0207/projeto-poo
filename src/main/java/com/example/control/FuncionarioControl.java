package com.example.control;

import java.util.List;
import com.example.daos.FuncionarioDAO;
import com.example.daos.IFuncionarioDAO;
import com.example.entity.Cliente;
import com.example.entity.Funcionario;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioControl {
	public IntegerProperty idFuncionario = new SimpleIntegerProperty(0);
    public StringProperty cargoFuncionario = new SimpleStringProperty("");
	public StringProperty  setor = new SimpleStringProperty("");

    private ObservableList<Funcionario> listaViewF = FXCollections.observableArrayList();
    private IFuncionarioDAO IFuncionarioDAO = new FuncionarioDAO();

    public Funcionario getEntity() {
    	Funcionario f = new Funcionario();
        f.setIdFuncionario(idFuncionario.get());
        f.setCargoFuncionario(cargoFuncionario.get());
        f.setSetor(setor.get());

        return f;
    }

    public void setEntity(Funcionario f) {
        idFuncionario.set(f.getIdFuncionario());
        cargoFuncionario.set(f.getCargoFuncionario());
        setor.set(f.getSetor());
    }

    public void salvar() {
    	Funcionario f = getEntity();
        boolean encontrado = false;

        for(int i = 0; i < listaViewF.size(); i++ ) {
            if (f.getIdFuncionario() == listaViewF.get(i).getIdFuncionario()) {
                encontrado = true;
            }
        }

        if (!encontrado) {
        	IFuncionarioDAO.adicionar(f);
        } else {
        	IFuncionarioDAO.atualizar(f);
        }
        atualizarListaView();
        setEntity(new Funcionario());
    }


    public void pesquisar() {
        listaViewF.clear();
        List<Funcionario> encontrados = IFuncionarioDAO.pesquisarPorCargo(cargoFuncionario.get());
        listaViewF.addAll(encontrados);
    }

    public void remover(int idFuncionario) {
        IFuncionarioDAO.remover(idFuncionario);
        atualizarListaView();
    }


    public void atualizarListaView() {
    	listaViewF.clear();
    	listaViewF.addAll(IFuncionarioDAO.pesquisarPorCargo(""));
    }

    public ObservableList<Funcionario> getListaView() {
        return listaViewF;
    }
}