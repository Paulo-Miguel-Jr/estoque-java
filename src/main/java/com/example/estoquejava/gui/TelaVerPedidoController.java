package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.ItemPedidoController;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaVerPedidoController implements Initializable {

    @FXML
    private TableColumn<ItemPedido, String> item;

    @FXML
    private TableColumn<ItemPedido, String> preco;

    @FXML
    private TableColumn<ItemPedido, String> quantidade;

    @FXML
    private Label labelPedido;


    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane anchor;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private TableView<ItemPedido> tableView;

    @FXML
    private Button voltar;

    private ItemPedidoController itemPedidoController;
    private ObservableList<ItemPedido> listaItens;
    private Pedido pedidoAtual;
    private PedidoController pedidoController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        listaItens = FXCollections.observableArrayList();
        pedidoController = new PedidoController();
    }

    private void configurarColunas() {
        item.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                return new SimpleStringProperty(item.getNomeProduto());
            } else {
                return new SimpleStringProperty("");
            }
        });

        quantidade.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                return new SimpleStringProperty(String.valueOf(item.getQuantidade()));
            } else {
                return new SimpleStringProperty("");
            }
        });

        preco.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                return new SimpleStringProperty(String.format("%.2f", item.getPrecoProduto()));
            } else {
                return new SimpleStringProperty("");
            }
        });
    }

    public void setPedido(Pedido pedido) {
        this.pedidoAtual = pedido;
        configurarColunas();
    }

    @FXML
    private void voltarTelaFinal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaFinal.fxml", "Tela Final");
    }

}
