package com.example.boundary;

import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.control.*;
import com.example.entity.*;


public class CarrinhoBoundary extends CommandProducer  implements StrategyBoundary {
  private TextField txtIdCarrinho = new TextField();
  private TextField txtIdProduto = new TextField();
  private TextField txtNomeProduto = new TextField();
  private TextField txtValorProduto = new TextField();
  private TextField txtTotal = new TextField();
  private ComboBox<String> cmbQtdeItem = new ComboBox<>();
  private TextField txtCodigoCarrinho = new TextField();


  private Button btnAdicionarCarrinho = new Button("Adicionar no Carrinho");
  private Button btnLimparCarrinho = new Button("Limpar Carrinho");
  private Button btnSalvarCarrinho = new Button("Salvar Carrinho");
  private Button btnBuscarProduto = new Button("Buscar");

  private CarrinhoControl control = new CarrinhoControl();
  private TableView<Carrinho> table = new TableView<>();

  private void criarTabela() {
      TableColumn<Carrinho, Integer> col1 = new TableColumn<>("Código Produto");
      col1.setCellValueFactory( new PropertyValueFactory<>("idProduto"));

      TableColumn<Carrinho, Integer> col2 = new TableColumn<>("Nome Produto");
      col2.setCellValueFactory( new PropertyValueFactory<>("nomeProduto"));

      TableColumn<Carrinho, Integer> col3 = new TableColumn<>("Qtde Produto");
      col3.setCellValueFactory(new PropertyValueFactory("qtdeItem"));

      TableColumn<Carrinho, Integer> col4 = new TableColumn<>("Valor Unitário");
      col4.setCellValueFactory( new PropertyValueFactory<>("valorProduto"));

      TableColumn<Carrinho, Integer> col5 = new TableColumn<>("Total");
      col5.setCellValueFactory( new PropertyValueFactory<>("total"));

      TableColumn<Carrinho, String> col6 = new TableColumn<>("Ações");
      col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
      col6.setCellFactory( (tbCol) ->
              new TableCell<Carrinho, String>() {
                  final Button btn = new Button("Remover");

                  public void updateItem(String item, boolean empty) {
                      if (empty) {
                          setGraphic(null);
                          setText(null);
                      } else {
                          btn.setOnAction( (e) -> {
                              Carrinho carrinho = getTableView().getItems().get(getIndex());
                              Alert alert = new Alert(Alert.AlertType.WARNING,
                                      "Você confirma a remoção do " +
                                              carrinho.getNomeProduto(), ButtonType.OK, ButtonType.CANCEL);
                              Optional<ButtonType> clicado = alert.showAndWait();
                              if (clicado.isPresent() &&
                                      clicado.get().equals(ButtonType.OK)) {
                                  control.remover(carrinho.getIdCarrinho(), carrinho.getIdProduto(), carrinho.getQtdeItem());
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


    @Override
    public BorderPane render() {
        BorderPane panPrincipal = new BorderPane();
        panPrincipal.setPadding(new Insets(15, 15, 15, 15));
        GridPane panCampos = new GridPane();
        GridPane panRodape = new GridPane();

        panCampos.setPadding(new Insets(0, 10, 10, 10));
        panCampos.setHgap(10.0);
        panCampos.setVgap(10.0);
        panRodape.setPadding(new Insets(10, 10, 10, 10));
        panRodape.setHgap(5.0);

        txtCodigoCarrinho.setEditable(false);
        txtCodigoCarrinho.setDisable(true);

        txtValorProduto.setEditable(false);
        txtValorProduto.setDisable(true);

        Bindings.bindBidirectional(txtIdCarrinho.textProperty(), control.idCarrinho, new NumberStringConverter());
        Bindings.bindBidirectional(txtCodigoCarrinho.textProperty(), control.codigoCarrinho, new NumberStringConverter());
        Bindings.bindBidirectional(txtIdProduto.textProperty(), control.idProduto, new NumberStringConverter());
        Bindings.bindBidirectional(cmbQtdeItem.valueProperty(), control.qtdeItem, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeProduto.textProperty(), control.nomeProduto);
        Bindings.bindBidirectional(txtValorProduto.textProperty(), control.valorProduto, new NumberStringConverter());
        Bindings.bindBidirectional(txtTotal.textProperty(), control.total, new NumberStringConverter());

        panCampos.add(new Label( "Produto: "), 1, 1);
        panCampos.add(txtIdProduto, 2, 1);

        panCampos.add(btnBuscarProduto, 3, 1);

        panCampos.add(new Label( "Nome: "), 4, 1);
        panCampos.add(txtNomeProduto, 5, 1);

        panCampos.add(new Label( "Valor: "), 6, 1);
        panCampos.add(txtValorProduto, 7, 1);

        panCampos.add(new Label( "Quantidade: "), 8, 1);
        panCampos.add(cmbQtdeItem, 9, 1);

        panCampos.add(new Label( "Total: "), 10, 1);
        cmbQtdeItem.setOnAction( e -> {
            txtTotal.setText(String.valueOf(calcularTotal(Double.parseDouble(txtValorProduto.getText()), Integer.parseInt(cmbQtdeItem.getValue()))));
        });
        panCampos.add(txtTotal, 11, 1);

        panCampos.add(btnAdicionarCarrinho, 11, 2);

        panRodape.add(new Label("Carrinho Nº: "), 1, 0);
        panRodape.add(txtCodigoCarrinho, 2, 0);
        panRodape.add(btnLimparCarrinho, 3, 0);
        panRodape.add(btnSalvarCarrinho, 5, 0);

        btnAdicionarCarrinho.setOnAction(this::adicionarLista);
        btnLimparCarrinho.setOnAction( e -> {
            control.limparCarrinho();
        });
        btnSalvarCarrinho.setOnAction( e -> {
            int codigoCarrinho = Integer.parseInt(txtCodigoCarrinho.getText());
            control.salvarCarrinho(codigoCarrinho);
            executeCommand("BOUNDARY-PEDIDO");
        });

        btnBuscarProduto.setOnAction(this::buscarProduto);

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        panPrincipal.setBottom(panRodape);
        this.criarTabela();
        return (panPrincipal);
    }


    private void adicionarLista(ActionEvent e) {
        control.adicionarCarrinho();
    }

    private void buscarProduto(ActionEvent e) {
        String[] Produto = control.verificarProduto(Integer.parseInt(txtIdProduto.getText()));
        double valor = Double.parseDouble(Produto[2]);
        txtNomeProduto.setText(Produto[0]);
        txtValorProduto.setText(String.valueOf(valor));

        ObservableList<String> qtdeItems =
                FXCollections.observableArrayList(verificarQtde(Produto[1]));
        cmbQtdeItem.setItems(qtdeItems);

        int codigoCarrinho = control.verificarCodigoCarrinho();
        txtCodigoCarrinho.setText(String.valueOf(codigoCarrinho));
    }

    private List<String> verificarQtde(String qtde) {
        List<String> listItems = new ArrayList<>();
        for(int i = 1; i <= Integer.parseInt(qtde); i++) {
            listItems.add(Integer.toString(i));
        }
        return listItems;
    }

    private Double calcularTotal(Double preco, int qtde) {
        Double total = ( preco * qtde );
        return total;
    }
}
