package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.models.enums.TipoUsuario;
import com.example.estoquejava.repository.UsuarioRepositorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TelaCadastroController {

    @FXML
    private TextField nomeField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private Button btnVoltar;

    @FXML
    private ComboBox<String> tipoUsuarioComboBox;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label statusLabel;

    private UsuarioRepositorio usuarioRepositorio;

    public TelaCadastroController() {
        usuarioRepositorio = UsuarioRepositorio.getInstance();
    }

    @FXML
    private void cadastrarUsuario() {
        String nome = nomeField.getText();
        String senha = senhaField.getText();
        String tipoUsuarioStr = tipoUsuarioComboBox.getValue();

        if (nome.isEmpty() || senha.isEmpty() || tipoUsuarioStr == null) {
            statusLabel.setText("Preencha todos os campos.");
            return;
        }

        TipoUsuario tipoUsuario;
        try {
            tipoUsuario = TipoUsuario.valueOf(tipoUsuarioStr);
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Tipo de usuário inválido.");
            return;
        }

        try {
            Usuario novoUsuario = new Usuario(nome, senha, usuarioRepositorio.listarUsuarios().length + 1, tipoUsuario);
            usuarioRepositorio.adicionarUsuario(novoUsuario);
            statusLabel.setText("Usuário cadastrado com sucesso!");
            limparCampos();
        } catch (IllegalArgumentException e) {
            statusLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void irParaTelaLogin(ActionEvent event){
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaLogin.fxml", "TelaLogin");
    }

    private void limparCampos() {
        nomeField.clear();
        senhaField.clear();
        tipoUsuarioComboBox.getSelectionModel().clearSelection();
    }
}
