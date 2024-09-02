package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {


    public TelaLoginController() {

    }
    @FXML
    private Button ok;

    @FXML
    private PasswordField senhaField;

    @FXML
    private Label senhaLabel;

    @FXML
    private PasswordField usuarioField;

    @FXML
    private Label usuarioLabel;


    public void irParaTela() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ok.setText("OK");
    }
}
