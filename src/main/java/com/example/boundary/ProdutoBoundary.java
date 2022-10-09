package com.example.boundary;

import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import com.example.control.ProdutoControl;
import com.example.entity.Produto;

import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

public class ProdutoBoundary extends CommandProducer implements StrategyBoundary {
	private TextField txtIdProduto = new TextField();
	private TextField txtNomeProduto = new TextField();
	private TextArea txtADescricaoProduto = new TextArea();
	private TextField txtQtdePecas = new TextField();
	private TextField txtPrecoProduto = new TextField();
	private Button btnSalvar = new Button("Salvar");
	private Button btnPesquisar = new Button("Pesquisar");

	private ProdutoControl control = new ProdutoControl();   // Composicao
	private TableView<Produto> table = new TableView<>();


	private void criarTabela() {
		TableColumn<Produto, Integer> col1 = new TableColumn<>("Id");
		col1.setCellValueFactory( new PropertyValueFactory<>("idProduto") );

		TableColumn<Produto, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory( new PropertyValueFactory<>("nomeProduto") );

		TableColumn<Produto, String> col3 = new TableColumn<>("Descrição");
		col3.setCellValueFactory( new PropertyValueFactory<>("descricaoProduto") );

		TableColumn<Produto, Integer> col4 = new TableColumn<>("Quantidade de Pecas");
		col4.setCellValueFactory(new PropertyValueFactory<>("quantidadeDePecas"));


		TableColumn<Produto, Double> col5 = new TableColumn<>("Preço Produto");
		col5.setCellValueFactory( new PropertyValueFactory<>("precoProduto") );


		TableColumn<Produto, String> col6 = new TableColumn<>("Ações");
		col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
		col6.setCellFactory( (tbCol) ->
				new TableCell<Produto, String>() {
					final Button btn = new Button("Remover");

					public void updateItem(String item, boolean empty) {
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction( (e) -> {
								Produto p = getTableView().getItems().get(getIndex());
								Alert alert = new Alert(Alert.AlertType.WARNING,
										"Você confirma a remoção do Produto Id " +
												p.getIdProduto(), ButtonType.OK, ButtonType.CANCEL);
								Optional<ButtonType> clicado = alert.showAndWait();
								if (clicado.isPresent() &&
										clicado.get().equals(ButtonType.OK)) {
									control.remover(p.getIdProduto());
								}
							});
							setGraphic(btn);
							setText(null);
						}
					}
				}
		);

		col1.setPrefWidth(150);
		col2.setPrefWidth(150);
		col3.setPrefWidth(150);
		col4.setPrefWidth(150);
		col5.setPrefWidth(150);
		col6.setPrefWidth(150);

		table.getColumns().addAll(col1, col2, col3, col4, col5, col6);
		table.setItems(control.getListaView());

		table
				.getSelectionModel()
				.selectedItemProperty()
				.addListener( (obs, antigo, novo) -> {
							if (novo != null) {
								control.setEntity(novo);
							}
						}
				);
	}


	public Pane render() {
		BorderPane panPrincipal = new BorderPane();
		GridPane panCampos = new GridPane();
		txtIdProduto.setEditable(false);
		txtIdProduto.setDisable(true);

		panPrincipal.setPadding(new Insets(15, 15, 15, 15));
		panCampos.setPadding(new Insets(0, 10, 10, 10));
		panCampos.setHgap(10.0);
		panCampos.setVgap(10.0);


		Bindings.bindBidirectional(txtIdProduto.textProperty(), control.idProduto, new NumberStringConverter());
		Bindings.bindBidirectional(txtNomeProduto.textProperty(), control.nomeProduto);
		Bindings.bindBidirectional(txtADescricaoProduto.textProperty(), control.descricaoProduto);
		Bindings.bindBidirectional(txtQtdePecas.textProperty(), control.quantidadeDePecas, new NumberStringConverter());
		Bindings.bindBidirectional(txtPrecoProduto.textProperty(), control.precoProduto, new NumberStringConverter());

		panCampos.add(new Label("Id Produto"), 0, 0);
		panCampos.add(txtIdProduto, 1, 0);

		panCampos.add(new Label("Nome Produto"), 0, 1);
		panCampos.add(txtNomeProduto, 1, 1);

		panCampos.add(new Label("Descrição"), 0, 2);
		panCampos.add(txtADescricaoProduto, 1, 2);

		panCampos.add(new Label("Quantidade de Produtos"), 0, 3);
		panCampos.add(txtQtdePecas, 1, 3);

		panCampos.add(new Label("Preço Produto"), 0, 4);
		panCampos.add(txtPrecoProduto, 1, 4);

		panCampos.add(btnSalvar, 1, 5);
		panCampos.add(btnPesquisar, 2, 5);


		btnSalvar.setOnAction(e -> {
			control.salvar();
		});

		btnPesquisar.setOnAction( e -> {
			control.pesquisar();
		});

		panPrincipal.setTop(panCampos);
		panPrincipal.setCenter(table);
		this.criarTabela();
		return (panPrincipal);
	}
}




