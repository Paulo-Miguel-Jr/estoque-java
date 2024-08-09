package com.example.estoquejava.helloJavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL resource = HelloApplication.class.getResource("teste.fxml");
        if (resource == null) {
            System.out.println("arquivo teste.fxml n encontrado");
            System.out.println(HelloApplication.class.getResource(""));
            return;
        }
        System.out.println(resource);

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}