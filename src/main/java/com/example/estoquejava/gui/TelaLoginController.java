package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.models.enums.TipoUsuario;
import com.example.estoquejava.repository.UsuarioRepositorio;
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

    @FXML
    private Label statusLabel;

    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ok.setText("OK");
        usuarioRepositorio = UsuarioRepositorio.getInstance();
        Usuario admin = new Usuario("admin", "admin123", 1, TipoUsuario.ADMIN);
        try {
            usuarioRepositorio.adicionarUsuario(admin);
        } catch (IllegalArgumentException e) {
            //vou fazer
        }
    }

    @FXML
    private void validarLogin(ActionEvent event) {
        String nomeUsuario = usuarioField.getText();
        String senha = senhaField.getText();

        Usuario usuario = usuarioRepositorio.buscarUsuarioPorNome(nomeUsuario);

        if (usuario == null) {
            statusLabel.setText("Usuário não encontrado.");
        } else if (!usuario.getSenha().equals(senha)) {
            statusLabel.setText("Senha incorreta.");
        } else {
            statusLabel.setText("Login realizado com sucesso!");
            irParaTela();
        }
    }

    private void irParaTela() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }
}
