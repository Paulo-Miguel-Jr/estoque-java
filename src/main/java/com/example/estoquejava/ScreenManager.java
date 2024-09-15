package com.example.estoquejava;

import com.example.estoquejava.gui.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stage;

    private Scene telaLogin;
    private Scene telaPrincipal;
    private Scene telaCadastro;
    private Scene telaPedido;
    private Scene telaFinal;
    private Scene telaProduto;

    private TelaLoginController telaLoginController;
    private TelaCadastroController telaCadastroController;
    private TelaPrincipalController telaPrincipalController;
    private TelaPedidoController telaPedidoController;
    private TelaFinalController telaFinalController;
    private TelaProdutoController telaProdutoController;

    private ScreenManager() {
        this.screenLoader();
    }

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public static Stage getStg() {
        return stage;
    }

    public static void setStg(Stage stage) {
        ScreenManager.stage = stage;
    }

    public Scene getTelaLogin() {
        return telaLogin;
    }

    public Scene getTelaPrincipal() {
        return telaPrincipal;
    }

    public Scene getTelaCadastro() {
        return telaCadastro;
    }

    public Scene getTelaPedido() {
        return telaPedido;
    }

    public Scene getTelaFinal() {
        return telaFinal;
    }

    public Scene getTelaProduto() {
        return telaProduto;
    }

    public TelaLoginController getTelaLoginController() {
        return telaLoginController;
    }

    public TelaPrincipalController getTelaPrincipalController() {
        return telaPrincipalController;
    }

    public TelaCadastroController getTelaCadastroController() {
        return telaCadastroController;
    }

    public TelaPedidoController getTelaPedidoController() {
        return telaPedidoController;
    }

    public TelaFinalController getTelaFinalController() {
        return telaFinalController;
    }

    public TelaProdutoController getTelaProdutoController() {
        return telaProdutoController;
    }


    private void screenLoader() {
        try {
            FXMLLoader loader;

            loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            this.telaLogin = new Scene(loader.load());
            this.telaLoginController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
            this.telaPrincipal = new Scene(loader.load());
            this.telaPrincipalController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
            this.telaCadastro = new Scene(loader.load());
            this.telaCadastroController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("TelaPedido.fxml"));
            this.telaPedido = new Scene(loader.load());
            this.telaPedidoController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("TelaFinal.fxml"));
            this.telaFinal = new Scene(loader.load());
            this.telaFinalController = loader.getController();

            loader = new FXMLLoader(getClass().getResource("TelaProduto.fxml"));
            this.telaProduto = new Scene(loader.load());
            this.telaProdutoController = loader.getController();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScreen(String fileNameFxml, String titleScreen) {
        boolean max = stage.isMaximized();
        if (max) stage.setMaximized(false);

        switch (fileNameFxml) {
            case "TelaLogin.fxml" -> stage.setScene(telaLogin);
            case "TelaPrincipal.fxml" -> {
                telaPrincipalController.atualizarTableView() ; stage.setScene(telaPrincipal);}
            case "TelaCadastro.fxml" -> stage.setScene(telaCadastro);
            case "TelaPedido.fxml" -> stage.setScene(telaPedido);
            case "TelaFinal.fxml" -> stage.setScene(telaFinal);
            case "TelaProduto.fxml" -> stage.setScene(telaProduto);
        }
        stage.setTitle(titleScreen);

        if (max) stage.setMaximized(true);
    }
}
