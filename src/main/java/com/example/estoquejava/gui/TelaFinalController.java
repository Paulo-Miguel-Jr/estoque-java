package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.repository.PedidoRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaFinalController implements Initializable {
    @FXML
    private Button buttonVer;

    @FXML
    private ImageView image;

    @FXML
    private Label labelValorFinalizado;

    @FXML
    private Button buttonCancelar;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button buttonOutroPedido;

    @FXML
    private Label labelFinalizado;


    @FXML
    private void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @FXML
    public void iniciarOutroPedido() {
        irParaTelaPrincipal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
