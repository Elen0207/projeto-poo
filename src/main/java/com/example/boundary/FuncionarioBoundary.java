package com.example.boundary;

import java.util.Optional;

import com.example.entity.Funcionario;
import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import com.example.control.FuncionarioControl;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

public class FuncionarioBoundary extends CommandProducer implements StrategyBoundary {

	private TextField txtIdFuncionario = new TextField();
	private TextField txtCargoFuncionario = new TextField();
	private TextField txtSetor = new TextField();


	private Button btnAdicionar = new Button("Adicionar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnSalvar = new Button("Salvar");

	private FuncionarioControl control = new FuncionarioControl();
	private TableView<Funcionario> tableFuncionario = new TableView<>();


	private void criarTabela() {
		TableColumn<Funcionario, Integer> col1 = new TableColumn<>("Id Funcionario");
		col1.setCellValueFactory( new PropertyValueFactory<>("idFuncionario") );

		TableColumn<Funcionario, String> col2 = new TableColumn<>("Cargo");
		col2.setCellValueFactory( new PropertyValueFactory<>("cargoFuncionario") );

		TableColumn<Funcionario, String> col3 = new TableColumn<>("Setor");
		col3.setCellValueFactory( new PropertyValueFactory<>("setor") );


		TableColumn<Funcionario, String> col4 = new TableColumn<>("Ações");
		col4.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
		col4.setCellFactory( (tbCol) ->
		new TableCell<Funcionario, String>() {
			final Button btn = new Button("Remover");

			public void updateItem(String item, boolean empty) {
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					btn.setOnAction( (e) -> {
						Funcionario f = getTableView().getItems().get(getIndex());
						Alert alert = new Alert(Alert.AlertType.WARNING,
								"Você confirma a remoção do Funcionário Id " +
										f.getIdFuncionario(), ButtonType.OK, ButtonType.CANCEL);
						Optional<ButtonType> clicado = alert.showAndWait();
						if (clicado.isPresent() &&
								clicado.get().equals(ButtonType.OK)) {
							control.remover(f.getIdFuncionario());
						}
					});
					setGraphic(btn);
					setText(null);
				}
			}
		});

		col1.setPrefWidth(150);
		col2.setPrefWidth(150);
		col3.setPrefWidth(150);
		col4.setPrefWidth(150);


		tableFuncionario.getColumns().addAll(col1, col2, col3, col4);
		tableFuncionario.setItems(control.getListaView());
		tableFuncionario
		.getSelectionModel()
		.selectedItemProperty()
		.addListener( (obs, antigo, novo) -> {
				if (novo != null) {
					control.setEntity(novo);
				}
		});
	}


  @Override
	public Pane render() {
		BorderPane panPrincipal = new BorderPane();
		GridPane panCampos = new GridPane();

		panPrincipal.setPadding(new Insets(15, 15, 15, 15));
		panCampos.setPadding(new Insets(0, 10, 10, 10));
		panCampos.setHgap(10.0);
		panCampos.setVgap(10.0);


		Bindings.bindBidirectional(txtIdFuncionario.textProperty(), control.idFuncionario, new NumberStringConverter());
		Bindings.bindBidirectional(txtCargoFuncionario.textProperty(), control.cargoFuncionario);
		Bindings.bindBidirectional(txtSetor.textProperty(), control.setor);

	  	txtIdFuncionario.setDisable(true);
	  	txtIdFuncionario.setEditable(false);

		panCampos.add(new Label("id Funcionario"), 0, 0);
		panCampos.add(txtIdFuncionario, 1, 0);

		panCampos.add(new Label("Cargo"), 0, 1);
		panCampos.add(txtCargoFuncionario, 1, 1);

		panCampos.add(new Label("Setor"), 0, 2);
		panCampos.add(txtSetor, 1, 2);


		panCampos.add(btnSalvar, 0, 4);
		panCampos.add(btnPesquisar, 1, 4);


		btnSalvar.setOnAction(e -> {
			control.salvar();
		});

		btnPesquisar.setOnAction( e -> {
			control.pesquisar();
		});

		panPrincipal.setTop(panCampos);
		panPrincipal.setCenter(tableFuncionario);
		this.criarTabela();
		return (panPrincipal);
	}
}








