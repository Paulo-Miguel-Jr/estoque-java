package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPedidoController implements Initializable {

    @FXML
    private SplitPane split;

    @FXML
    private ImageView image;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private Label labelPedido;

    @FXML
    private Button adicionarItem;

    @FXML
    private Button cancelarPedido;

    @FXML
    private Button criarPedido;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

}
