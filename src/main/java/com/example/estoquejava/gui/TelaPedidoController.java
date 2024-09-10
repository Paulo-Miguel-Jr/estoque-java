package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.repository.PedidoRepositorio;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<ItemPedido> tableView;

    @FXML
    private TableColumn<ItemPedido, String> colunaItem;

    @FXML
    private TableColumn<ItemPedido, Double> colunaPreco;

    @FXML
    private TableColumn<ItemPedido, Integer> colunaQuantidade;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane anchor2;

    @FXML
    private AnchorPane anchor1;


    @FXML
    private Label labelValorTotal;

    @FXML
    private SplitPane split;

    @FXML
    private Button cancelarPedido;

    @FXML
    private AnchorPane anchorInScroll;

    @FXML
    private Label labelCarrinho;


    private ObservableList<ItemPedido> listaItens = FXCollections.observableArrayList();

    private Pedido pedidoAtual;
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @FXML
    public void adicionarItem() {
        irParaTelaPrincipal();
    }

    public void cancelarPedido() {
        tableView.getItems().clear();
        labelValorTotal.setText("R$ 0.00");
        atualizarTotal();
    }

    private void atualizarTotal() {
        if (pedidoAtual != null) {
            double total = pedidoAtual.calcularValorTotal();
            labelValorTotal.setText("R$ " + String.format("%.2f", total));
        } else {
            labelValorTotal.setText("R$ 0.00");
        }
    }

}
