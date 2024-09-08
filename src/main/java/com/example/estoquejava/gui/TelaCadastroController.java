package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaCadastroController {

    @FXML
    private Button BtnCadastrar;

    @FXML
    private Button BtnVoltar;

    @FXML
    private PasswordField senhaField;

    @FXML
    private PasswordField senhaFieldConfirm;

    @FXML
    private TextField senhaVisivelField;

    @FXML
    private TextField usuarioField;

    @FXML
    void cadastrarUsuario(ActionEvent event) {

    }

    @FXML
    private void irParaTelaLogin(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaLogin.fxml", "TelaLogin");
    }

}
