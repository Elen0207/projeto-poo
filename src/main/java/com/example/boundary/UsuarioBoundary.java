package com.example.boundary;

import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import com.example.control.UsuariosControl;
import com.example.entity.Usuarios;
import java.util.Optional;

public class UsuarioBoundary extends CommandProducer implements StrategyBoundary  {
    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtCEP = new TextField();
    private TextField txtRuaNumero = new TextField();
    private TextField txtCidade = new TextField();
    private TextField txtEstado = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtTipoUsuario = new TextField();
    private TextField txtLogin = new TextField();
    private TextField txtSenha = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");
    private Button btnAtualizar = new Button("Atualizar");


    private UsuariosControl control = new UsuariosControl();   // Composição
    private TableView<Usuarios> table = new TableView<>();

    private void criarTabela() {
        TableColumn<Usuarios, Long> col1 = new TableColumn<>("Id ");
        col1.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));

        TableColumn<Usuarios, String> col2 = new TableColumn<>("Nome ");
        col2.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));

        TableColumn<Usuarios, Long> col3 = new TableColumn<>("CEP ");
        col3.setCellValueFactory(new PropertyValueFactory<>("CEP"));

        TableColumn<Usuarios, String> col4 = new TableColumn<>("Rua e Numero ");
        col4.setCellValueFactory(new PropertyValueFactory<>("ruaEnderecoNumero"));

        TableColumn<Usuarios, Double> col5 = new TableColumn<>("Cidade ");
        col5.setCellValueFactory(new PropertyValueFactory<>("cidadeEndereco"));

        TableColumn<Usuarios, String> col6 = new TableColumn<>("Estado ");
        col6.setCellValueFactory(new PropertyValueFactory<>("estadoEndereco"));

        TableColumn<Usuarios, String> col7 = new TableColumn<>("Telefone ");
        col7.setCellValueFactory(new PropertyValueFactory<>("telefoneUsuario"));

        TableColumn<Usuarios, String> col8 = new TableColumn<>("Tipo Usuario ");
        col8.setCellValueFactory(new PropertyValueFactory<>("tipoUsuario"));

        TableColumn<Usuarios, String> col9 = new TableColumn<>("Login ");
        col9.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<Usuarios, String> col10 = new TableColumn<>("Senha ");
        col10.setCellValueFactory(new PropertyValueFactory<>("senha"));

        TableColumn<Usuarios, String> col11 = new TableColumn<>("Ações");
        col11.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col11.setCellFactory((tbCol) ->
                new TableCell<Usuarios, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction((e) -> {
                                Usuarios p = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Usuario Id " +
                                                p.getIdUsuario(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(p.getIdUsuario());
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
        col6.setPrefWidth(100);
        col7.setPrefWidth(100);
        col8.setPrefWidth(100);
        col9.setPrefWidth(100);
        col10.setPrefWidth(100);
        col11.setPrefWidth(100);

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11);

        table.setItems(control.getListaView());

        table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, antigo, novo) -> {
                            if (novo != null) {
                                control.setEntity(novo);
                            }
                        }
                );
    }

    public BorderPane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panPrincipal.setPadding(new Insets(15, 15, 15, 15));
        panCampos.setPadding(new Insets(0, 10, 10, 10));
        panCampos.setHgap(10.0);
        panCampos.setVgap(10.0);


        txtId.setEditable(false);
        txtId.setDisable(true);
        Bindings.bindBidirectional(txtId.textProperty(), control.idUsuario, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeUsuario);
        Bindings.bindBidirectional(txtCEP.textProperty(), control.CEP, new NumberStringConverter());
        Bindings.bindBidirectional(txtRuaNumero.textProperty(), control.ruaEnderecoNumero);
        Bindings.bindBidirectional(txtCidade.textProperty(), control.cidadeEndereco);
        Bindings.bindBidirectional(txtEstado.textProperty(), control.estadoEndereco);
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneUsuario, new NumberStringConverter());
        Bindings.bindBidirectional(txtTipoUsuario.textProperty(), control.tipoUsuario);
        Bindings.bindBidirectional(txtLogin.textProperty(), control.login);
        Bindings.bindBidirectional(txtSenha.textProperty(), control.senha);

        panCampos.add(new Label("Id "), 0, 0);
        panCampos.add(txtId, 1, 0);

        panCampos.add(new Label("Nome Usuario "), 0, 1);
        panCampos.add(txtNome, 1, 1);
        txtTipoUsuario.setPrefWidth(400.0);                     // definicao do tamanho do textfield
        txtNome.setPromptText("Nome Completo do Usuario");      // texto de instrução

        panCampos.add(new Label("CEP da Rua"), 0, 2);
        panCampos.add(txtCEP, 1, 2);
        txtCEP.setPromptText(" CEP da rua ");

        panCampos.add(new Label("Rua e Numero "), 0, 3);
        panCampos.add(txtRuaNumero, 1, 3);
        txtRuaNumero.setPromptText(" Nome da rua e numero");

        panCampos.add(new Label("Cidade "), 0, 4);
        panCampos.add(txtCidade, 1, 4);
        txtCidade.setPromptText(" Nome da Cidade");

        panCampos.add(new Label("UF "), 0, 5);
        panCampos.add(txtEstado, 1, 5);
        txtEstado.setPromptText(" Sigla do Estado");

        panCampos.add(new Label("Telefone "), 0, 6);
        panCampos.add(txtTelefone, 1, 6);

        panCampos.add(new Label("Tipo Usuario "), 0, 7);
        panCampos.add(txtTipoUsuario, 1, 7);
        txtTipoUsuario.setPromptText("C = cliente ou F = funcionario");

        panCampos.add(new Label("Login Usuario "), 0, 8);
        panCampos.add(txtLogin, 1, 8);
        txtLogin.setPromptText("e-mail do usuario");

        panCampos.add(new Label("Senha Usuario "), 0, 9);
        panCampos.add(txtSenha, 1, 9);
        txtSenha.setPromptText("Senha do usuario");

        panCampos.add(btnSalvar, 0, 10);
        panCampos.add(btnPesquisar, 1, 10);
        panCampos.add(btnAtualizar, 2, 10);

        btnSalvar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction( e -> {
            control.pesquisar();
        });

        btnAtualizar.setOnAction( e -> {
            control.atualizarU();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();

        return (panPrincipal);
    }

} // fim da classe
