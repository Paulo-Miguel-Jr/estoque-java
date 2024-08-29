package com.example.estoquejava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ScreenManager.setStg(stage);
        //URL resource = Application.class.getResource("TelaLogin.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("TelaLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}