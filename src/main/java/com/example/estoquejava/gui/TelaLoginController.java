package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.UsuarioRepositorio;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaLoginController implements Initializable {

    @FXML
    private Button BtnLogin;

    @FXML
    private Button BtnCadastrar;


    @FXML
    private PasswordField senhaField;

    @FXML
    private TextField usuarioField;

    @FXML
    private TextField senhaVisivelField;

    @FXML
    private Button btnMostrarSenha;

    @FXML
    private Label statusLabel;

    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioRepositorio = UsuarioRepositorio.getInstance();
        Usuario admin = new Usuario("admin", "admin123", 1);
        try {
            usuarioRepositorio.adicionarUsuario(admin);
        } catch (IllegalArgumentException e) {
            //vou fazer
        }
        senhaVisivelField.setVisible(false);
        Platform.runLater(() -> { //serve pra que a aplicação comece sem foco nos componentes
            statusLabel.requestFocus();
        });
    }

    @FXML
    private void validarLogin(ActionEvent event) {
        String nomeUsuario = usuarioField.getText();
        String senha;

        if (senhaField.isVisible()) {
            senha = senhaField.getText();
        } else {
            senha = senhaVisivelField.getText();
        }


        try {
            Usuario usuario = usuarioRepositorio.buscarUsuarioPorNome(nomeUsuario);

            if (usuario == null) {
                statusLabel.setText("Usuário não encontrado.");
            } else {

                if (!usuario.getSenha().equals(senha)) { //comparar apenas com a senha do campo
                    statusLabel.setText("Senha incorreta.");

                } else {
                    irParaTela();

                }
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", e.getMessage());
            System.out.println("Erro no login");
        }
    }



    private void irParaTela() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @FXML
    private void irParaTelaCadastro(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaCadastro.fxml", "TelaCadastro");
    }

    @FXML
    private void alternarSenhaVisibilidade(ActionEvent event) {
        if (senhaField.isVisible()) {
            senhaVisivelField.setText(senhaField.getText());
            senhaVisivelField.setVisible(true);
            senhaField.setVisible(false);
            btnMostrarSenha.setText("Mostrar");
        } else {
            senhaField.setText(senhaVisivelField.getText());
            senhaField.setVisible(true);
            senhaVisivelField.setVisible(false);
            btnMostrarSenha.setText("Ocultar");
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
