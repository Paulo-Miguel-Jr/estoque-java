package com.example.estoquejava.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class ScreenManager {
    private static ScreenManager instance;
    private static Stage stg;

    private Scene TelaLogin;
    private Scene TelaPrincipal;

    private TelaLoginController telaLoginController;


    private TelaPrincipalController telaPrincipalController;

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
        return TelaLogin;
    }

    public Scene getTelaPrincipal(){
        return TelaPrincipal;
    }


    //Getters dos Controllers
    public TelaLoginController getTelaLoginController(){
        return telaLoginController;
    }

    public TelaPrincipalController getTelaPrincipalController(){
        return telaPrincipalController;
    }

    private void screenLoader(){
        try {
            FXMLLoader TelaLoginPane = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
            this.TelaLogin = new Scene(TelaLoginPane.load());
            this.telaLoginController = TelaLoginPane.getController();

            FXMLLoader TelaPrincipalPane = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
            this.TelaPrincipal = new Scene(TelaPrincipalPane.load());
            this.telaPrincipalController = TelaPrincipalPane.getController();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeScreen(String fileNameFxml, String titleScreen){
        boolean max = stg.isMaximized();
        if(max) stg.setMaximized(false);

        switch (fileNameFxml){
            case "TelaLogin.fxml" -> stg.setScene(TelaLogin);
            case "TelaPrincipal.fxml" -> stg.setScene(TelaPrincipal);
        }
        stg.setTitle(titleScreen);

        if(max) stg.setMaximized(true);

    }





}
