package com.example.estoquejava;

import com.example.estoquejava.gui.TelaCadastroController;
import com.example.estoquejava.gui.TelaLoginController;
import com.example.estoquejava.gui.TelaPedidoController;
import com.example.estoquejava.gui.TelaPrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    private Scene telaLogin;
    private Scene telaPrincipal;
    private Scene telaCadastro;
    private Scene telaPedido;

    private TelaLoginController telaLoginController;

    private TelaCadastroController telaCadastroController;

    private TelaPrincipalController telaPrincipalController;

    private TelaPedidoController telaPedidoController;


    public ScreenManager(){
        this.screenLoader();
    }

    //Singleton para uma única instancia das telas.
    public static ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }



    //Getter e setter do Stage
    public static Stage getStg(){
        return stg;
    }

    public static void setStg(Stage stg){
        ScreenManager.stg = stg;
    }

    //Getters das Scenes
    public Scene getTelaLogin(){
        return telaLogin;
    }
    public Scene getTelaPrincipal(){
        return telaPrincipal;
    }
    public Scene getTelaCadastro() {
        return telaCadastro;
    }
    public Scene getTelaPedido() {
        return telaPedido;
    }



    //Getters dos Controllers
    public TelaLoginController getTelaLoginController(){
        return telaLoginController;
    }

    public TelaPrincipalController getTelaPrincipalController(){
        return telaPrincipalController;
    }

    public TelaCadastroController getTelaCadastroController() {
        return telaCadastroController;
    }

    public TelaPedidoController getTelaPedidoController() {
        return telaPedidoController;
    }

    private void screenLoader(){
        try {
            FXMLLoader TelaLoginPane = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            this.telaLogin = new Scene(TelaLoginPane.load());
            this.telaLoginController = TelaLoginPane.getController();

            FXMLLoader TelaPrincipalPane = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
            this.telaPrincipal = new Scene(TelaPrincipalPane.load());
            this.telaPrincipalController = TelaPrincipalPane.getController();

            FXMLLoader TelaCadastroPane = new FXMLLoader(getClass().getResource("TelaCadastro.fxml"));
            this.telaCadastro = new Scene(TelaCadastroPane.load());
            this.telaCadastroController = TelaCadastroPane.getController();

            FXMLLoader TelaPedidoPane = new FXMLLoader(getClass().getResource("TelaPedido.fxml"));
            this.telaPedido = new Scene(TelaPedidoPane.load());
            this.telaPedidoController = TelaPedidoPane.getController();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScreen(String fileNameFxml, String titleScreen){
        boolean max = stg.isMaximized();
        if(max) stg.setMaximized(false);

        switch (fileNameFxml){
            case "TelaLogin.fxml" -> stg.setScene(telaLogin);
            case "TelaPrincipal.fxml" -> stg.setScene(telaPrincipal);
            case "TelaCadastro.fxml" -> stg.setScene(telaCadastro);
            case "TelaPedido.fxml" -> stg.setScene(telaPedido);
        }
        stg.setTitle(titleScreen);

        if(max) stg.setMaximized(true);

    }





}
