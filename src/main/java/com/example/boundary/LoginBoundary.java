package com.example.boundary;

import com.example.main.CommandProducer;
import com.example.main.StrategyBoundary;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import com.example.control.LoginControl;


public class LoginBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtNome = new TextField();
    private TextField txtLogin = new TextField();
    private TextField txtSenha = new TextField();
    private TextField txtMensagem = new TextField();
    private Button btnValidar = new Button("Validar");
    private Button btnEsqueciSenha = new Button("Esqueci a Senha");
    private LoginControl controlLog = new LoginControl();   // Composição


    @Override
    public BorderPane render () {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panPrincipal.setPadding(new Insets(15, 15, 15, 15));
        panCampos.setPadding(new Insets(0, 10, 10, 10));
        panCampos.setHgap(10.0);
        panCampos.setVgap(10.0);

        txtMensagem.setDisable(true);

        Bindings.bindBidirectional(txtNome.textProperty(), controlLog.nome );
        Bindings.bindBidirectional(txtLogin.textProperty(), controlLog.login );
        Bindings.bindBidirectional(txtSenha.textProperty(), controlLog.senha);
        Bindings.bindBidirectional(txtMensagem.textProperty(), controlLog.mensagen);

        panCampos.add(new Label("Nome "), 0, 0);
        panCampos.add(txtNome, 1, 0);
        txtNome.setPromptText("nome do usuario");

        panCampos.add(new Label("Login "), 0, 1);
        panCampos.add(txtLogin, 1, 1);
        txtLogin.setPromptText("Login do usuario");

        panCampos.add(new Label("Senha "), 0, 2);
        panCampos.add(txtSenha, 1, 2);
        txtSenha.setPromptText("Senha do usuario");

        panCampos.add(btnValidar, 0, 3);
        panCampos.add(btnEsqueciSenha, 1, 3);

        panCampos.add(new Label(""),0,5);
        panCampos.add(txtMensagem, 1, 5);
        txtMensagem.setPrefWidth(400.0);                     // definicao do tamanho do textfield

        btnValidar.setOnAction(e -> {
            controlLog.validar();
        });


        btnEsqueciSenha.setOnAction( e -> {
            controlLog.esqueciSenha();
        });

        panPrincipal.setTop(panCampos);

        return (panPrincipal);
    }
}
