package com.example.estoquejava.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    private ObservableList<String> listaDeOpcoes = FXCollections.observableArrayList();

    public TelaLoginController() {
        listaDeOpcoes.add("Opção A");
        listaDeOpcoes.add("Opção B");
        listaDeOpcoes.add("Opção C");
    }
    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    public void setarLabel() {
        label.setText("Mudamos a label.");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(listaDeOpcoes);
        label.setText("Hello World");
    }
}
