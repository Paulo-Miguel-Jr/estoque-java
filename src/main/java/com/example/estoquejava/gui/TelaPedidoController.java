package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPedidoController implements Initializable {

    @FXML
    private Button adicionarItem;

    @FXML
    private ImageView image;

    @FXML
    private Label labelTotal;

    @FXML
    private Button criarPedido;

    @FXML
    private TableColumn<?, ?> colunaItem;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TableColumn<?, ?> colunaPreco;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private AnchorPane anchor1;

    @FXML
    private TableView<?> tableView;

    @FXML
    private Label labelValorTotal;

    @FXML
    private TableColumn<?, ?> colunaQuantidade;

    @FXML
    private SplitPane split;

    @FXML
    private Button cancelarPedido;

    @FXML
    private AnchorPane anchorInScroll;

    @FXML
    private Label labelCarrinho;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

}
