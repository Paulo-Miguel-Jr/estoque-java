package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.models.UsuarioController;
import com.example.estoquejava.repository.UsuarioRepositorio;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TextField usuarioField;

    private UsuarioController usuarioController;


    public TelaCadastroController() {
        UsuarioRepositorio repositorio = UsuarioRepositorio.getInstance();
        usuarioController = new UsuarioController();
        Platform.runLater(() -> { //serve pra que a aplicação comece sem foco nos componentes
            usuarioField.requestFocus();
        });
    }


    @FXML
    void cadastrarUsuario(ActionEvent event) {
        String usuario = usuarioField.getText();
        String senha = senhaField.getText();
        String senhaConfirm = senhaFieldConfirm.getText();
        validarSenha(usuario, senha, senhaConfirm);


    }


    @FXML
    private void irParaTelaLogin() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaLogin.fxml", "TelaLogin");
    }


    private void validarSenha(String usuario, String senha, String senhaConfirm) {
        if (usuario == null || usuario.trim().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erro", "O nome de usuário não pode ser vazio.");
            return;
        }

        if (senha == null || senha.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Erro", "A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        if (!senha.equals(senhaConfirm)) {
            showAlert(Alert.AlertType.ERROR, "Erro", "As senhas não coincidem.");
            return;
        }

        UsuarioRepositorio repositorio = UsuarioRepositorio.getInstance();
        int idUnico = repositorio.gerarIdUnico();
        Usuario novoUsuario = new Usuario(usuario, senha, idUnico);

        try {
            usuarioController.cadastrarUsuario(novoUsuario);
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Usuário cadastrado com sucesso.");
            irParaTelaLogin();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
