package com.example.boundary;

import com.example.control.ClienteControl;
import com.example.entity.Cliente;
import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.util.Optional;

public class ClienteBoundary extends CommandProducer implements StrategyBoundary {
    
    private TextField txtIdCliente = new TextField();
    private TextField txtNomeCliente = new TextField();
    private TextField txtCPF = new TextField();
    private TextField txtBandeiraCartao = new TextField();
    private TextField txtNumeroCartao = new TextField();
    private TextField txtNomeNoCartao = new TextField();
    private TextField txtValidadeCartao = new TextField();
    private TextField txtCodigoSeguranca = new TextField();

    
    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAtualizar = new Button("Atualizar");

    private ClienteControl controlL = new ClienteControl();   // Composição
    private TableView<Cliente> tableL = new TableView<>();

    private void criarTabela() {
        TableColumn<Cliente, Integer> col1 = new TableColumn<>("Id Cliente ");
        col1.setCellValueFactory( new PropertyValueFactory<>("idCliente") );

        TableColumn<Cliente, String> col2 = new TableColumn<>("Nome ");
        col2.setCellValueFactory( new PropertyValueFactory<>("nomeCliente") );

        TableColumn<Cliente, Long> col3 = new TableColumn<>("CPF ");
        col3.setCellValueFactory( new PropertyValueFactory<>("CPF") );

        TableColumn<Cliente, Double> col4 = new TableColumn<>("Bandeira Cartao ");
        col4.setCellValueFactory( new PropertyValueFactory<>("bandeiraCartao") );

        TableColumn<Cliente, Long> col5 = new TableColumn<>("Numero Cartao ");
        col5.setCellValueFactory(new PropertyValueFactory<>("numeroCartao"));

        TableColumn<Cliente, String> col6 = new TableColumn<>("Nome no Cartao ");
        col6.setCellValueFactory(new PropertyValueFactory<>("nomeNoCartao"));

        TableColumn<Cliente, String> col7 = new TableColumn<>("Validade do Cartao ");
        col7.setCellValueFactory(new PropertyValueFactory<>("validade"));

        TableColumn<Cliente, String> col8 = new TableColumn<>("Codigo de Segurança ");
        col8.setCellValueFactory(new PropertyValueFactory<>("seguranca"));


        TableColumn<Cliente, String> col9 = new TableColumn<>("Ações");
        col9.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col9.setCellFactory( (tbCol) ->
                new TableCell<Cliente, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Cliente p = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Cliente " +
                                                p.getIdCliente(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    controlL.remover(p.getIdCliente());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        col1.setPrefWidth(100);
        col2.setPrefWidth(100);
        col3.setPrefWidth(100);
        col4.setPrefWidth(100);
        col5.setPrefWidth(100);
        col6.setPrefWidth(150);
        col7.setPrefWidth(150);
        col8.setPrefWidth(150);
        col9.setPrefWidth(100);

        tableL.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);

        tableL.setItems(controlL.getListaView());

        tableL
                .getSelectionModel()
                .selectedItemProperty()
                .addListener( (obs, antigo, novo) -> {
                            if (novo != null) {
                                controlL.setEntity(novo);
                            }
                        }
                );
    }
    @Override
    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panPrincipal.setPadding(new Insets(15, 15, 15, 15));
        panCampos.setPadding(new Insets(0, 10, 10, 10));
        panCampos.setHgap(10.0);
        panCampos.setVgap(10.0);


        txtIdCliente.setEditable(false);
        txtIdCliente.setDisable(true);
        Bindings.bindBidirectional(txtIdCliente.textProperty(), controlL.idCiente, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeCliente.textProperty(), controlL.nomeCliente);
        Bindings.bindBidirectional(txtCPF.textProperty(), controlL.CPF, new NumberStringConverter());
        Bindings.bindBidirectional(txtBandeiraCartao.textProperty(), controlL.bandeiraCartao);
        Bindings.bindBidirectional(txtNumeroCartao.textProperty(), controlL.numeroCartao, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeNoCartao.textProperty(), controlL.nomeNoCartao);
        Bindings.bindBidirectional(txtValidadeCartao.textProperty(), controlL.validade);
        Bindings.bindBidirectional(txtCodigoSeguranca.textProperty(), controlL.seguranca);


        panCampos.add(new Label("Id Cliente "), 0, 0);
        panCampos.add(txtIdCliente, 1, 0);

        panCampos.add(new Label("Nome Cliente "), 0, 1);
        panCampos.add(txtNomeCliente, 1, 1);
        txtNomeCliente.setPrefWidth(400.0);                     // definicao do tamanho do textfield
        txtNomeCliente.setPromptText("Nome Completo do Usuario");      // texto de instrução

        panCampos.add(new Label("CPF Cliente"), 0, 2);
        panCampos.add(txtCPF, 1, 2);
        txtCPF.setPromptText(" CPF do Cliente ");

        panCampos.add(new Label("Bandeira do Cartao "), 0, 3);
        panCampos.add(txtBandeiraCartao, 1, 3);
        txtBandeiraCartao.setPromptText(" bandeiraDoCartao");

        panCampos.add(new Label("Numero do Cartao "), 0, 4);
        panCampos.add(txtNumeroCartao, 1, 4);
        txtNumeroCartao.setPromptText(" Numero do Cartao sem espaço");

        panCampos.add(new Label("Nome no Cartao "), 0, 5);
        panCampos.add(txtNomeNoCartao, 1, 5);
        txtNomeNoCartao.setPromptText(" Nome no Cartao ");

        panCampos.add(new Label("Validade do Cartao "), 0, 6);
        panCampos.add(txtValidadeCartao, 1, 6);

        panCampos.add(new Label("Codigo de Segurança "), 0, 7);
        panCampos.add(txtCodigoSeguranca, 1, 7);
        txtCodigoSeguranca.setPromptText("Codigo de Segurança (verso do cartao) ");

        panCampos.add(btnSalvar, 0, 8);
        panCampos.add(btnPesquisar, 1, 8);
        panCampos.add(btnAtualizar, 2, 8);

        btnSalvar.setOnAction(e -> {
            controlL.salvar();
        });

        btnPesquisar.setOnAction(e -> {
            controlL.pesquisarCliente();
        });

        btnAtualizar.setOnAction(e -> {
            controlL.atualizar();
        });


        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(tableL);
        this.criarTabela();
        Scene scn = new Scene(panPrincipal, 900, 600);

        return (panPrincipal);
    }
}