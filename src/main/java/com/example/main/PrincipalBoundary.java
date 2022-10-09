package com.example.main;

import com.example.boundary.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class PrincipalBoundary extends Application implements CommandExecution {
    private UsuarioBoundary usuarioBoundary = new UsuarioBoundary();
    private ProdutoBoundary produtoBoundary = new ProdutoBoundary();
    private CarrinhoBoundary carrinhoBoundary = new CarrinhoBoundary();
    private LoginBoundary loginBoundary = new LoginBoundary();
    private FuncionarioBoundary funcionarioBoundary = new FuncionarioBoundary();
    private ClienteBoundary clienteBoundary = new ClienteBoundary();
    private PedidoBoundary pedidoBoundary = new PedidoBoundary();

    private BorderPane panePrincipal = new BorderPane();

    public PrincipalBoundary() {
        usuarioBoundary.addExecution(this);
        produtoBoundary.addExecution(this);
        carrinhoBoundary.addExecution(this);
        loginBoundary.addExecution(this);
        funcionarioBoundary.addExecution(this);
        clienteBoundary.addExecution(this);
        pedidoBoundary.addExecution(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(panePrincipal, 1200, 850);
        MenuBar menuPrincipal = new MenuBar();

        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");
        Menu menuRealizarPedido = new Menu("Realizar Pedido");

        MenuItem Sair = new MenuItem("Sair");
        MenuItem Login = new MenuItem("Login");
        MenuItem Carrinho = new MenuItem("Carrinho");
        MenuItem CadastrarCliente = new MenuItem("Cadastrar Cliente");
        MenuItem CadastrarFuncionario = new MenuItem("Cadastrar Funcionario");
        MenuItem CadastrarProduto = new MenuItem("Cadastrar Produto");
        MenuItem CadastrarUsuario = new MenuItem("Cadastrar Usuario");
        MenuItem RealizarPedido = new MenuItem("Realizar Pedido");

        Sair.setOnAction((e) -> {
            Platform.exit();
            System.exit(0);
        });

        Carrinho.setOnAction((e) -> {
            execute("BOUNDARY-CARRINHO");
        });
        CadastrarCliente.setOnAction((e) -> {
            execute("BOUNDARY-CLIENTE");
        });
        CadastrarProduto.setOnAction((e) -> {
            execute("BOUNDARY-PRODUTO");
        });
        CadastrarFuncionario.setOnAction((e) -> {
            execute("BOUNDARY-FUNCIONARIO");
        });
        CadastrarUsuario.setOnAction((e) -> {
            execute("BOUNDARY-USUARIO");
        });
        Login.setOnAction((e) -> {
            execute("BOUNDARY-LOGIN");
        });
        RealizarPedido.setOnAction((e) -> {
            execute("BOUNDARY-PEDIDO");
        });

        menuArquivo.getItems().addAll(Login, Sair);
        menuCadastros.getItems().addAll(CadastrarUsuario, CadastrarCliente, CadastrarFuncionario, CadastrarProduto);
        menuRealizarPedido.getItems().addAll(Carrinho);

        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuRealizarPedido);
        panePrincipal.setTop(menuPrincipal);

        stage.setScene(scn);
        stage.setTitle("Loja de Eletronicos");
        stage.show();
    }

    @Override
    public void execute(String command) {
        switch (command) {
            case "BOUNDARY-LOGIN":
                panePrincipal.setCenter(loginBoundary.render());
                break;
            case "BOUNDARY-USUARIO" :
                panePrincipal.setCenter(usuarioBoundary.render());
                break;
            case "BOUNDARY-FUNCIONARIO":
                panePrincipal.setCenter(funcionarioBoundary.render());
                break;
            case "BOUNDARY-PRODUTO":
                panePrincipal.setCenter(produtoBoundary.render());
                break;
            case "BOUNDARY-CLIENTE":
                panePrincipal.setCenter(clienteBoundary.render());
                break;
            case "BOUNDARY-CARRINHO":
                panePrincipal.setCenter(carrinhoBoundary.render());
                break;
            case "BOUNDARY-PEDIDO":
                panePrincipal.setCenter(pedidoBoundary.render());
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }
}
