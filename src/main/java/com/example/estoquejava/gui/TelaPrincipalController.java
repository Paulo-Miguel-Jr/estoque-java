package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane border;

    @FXML
    private Button buttonAdcProd;

    @FXML
    private Button buttonRmvProd;

    @FXML
    private TableColumn<Produto, String> colunaPreco;

    @FXML
    private TableColumn<Produto, String> colunaProduto;

    @FXML
    private TableColumn<Produto, String> colunaQuantidade;

    @FXML
    private Button irCarrinho;

    @FXML
    private TableView<Produto> tabela;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        irCarrinho.setText("Avancar");
    }

    @FXML
    private void irParaTela(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPedido.fxml", "TelaPedido");
    }

}
