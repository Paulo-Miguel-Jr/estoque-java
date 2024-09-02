package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    @FXML
    private Button ok;

    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField usuarioField;

    public void irParaTela() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ok.setText("OK");
    }
}
