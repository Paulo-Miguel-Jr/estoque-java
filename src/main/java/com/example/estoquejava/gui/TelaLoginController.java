package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        label.setText(comboBox.getValue());
    }

    public void irParaTela() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(listaDeOpcoes);
        comboBox.setValue(listaDeOpcoes.get(0));
        label.setText(comboBox.getValue());

        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                label.setText(newValue);
            }
        });

        button.setText("Trocar Tela");
    }
}
