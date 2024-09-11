package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.repository.ItemPedidoRepositorio;
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
import java.util.Optional;
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

    private ItemPedido itemPedido;
    private ItemPedidoRepositorio itemPedidoRepositorio;
    private PedidoRepositorio pedidoRepositorio;
    private PedidoController pedidoController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    private void irParaTelaLogin() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaLogin.fxml", "TelaLogin");
    }

    @FXML
    public void adicionarItem() {
        irParaTelaPrincipal();
    }

    @FXML
    public void cancelarPedido() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Cancelamento");
        alert.setHeaderText("Você realmente deseja cancelar o pedido?");
        alert.setContentText("O pedido será cancelado e a tela será resetada.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Simula o cancelamento do pedido, mesmo que ainda não tenha sido criado
                if (pedidoAtual != null) {
                    pedidoAtual.setStatus(StatusPedido.CANCELADO);
                    pedidoRepositorio.atualizarPedido(pedidoAtual);
                }


                tableView.getItems().clear();
                labelValorTotal.setText("R$ 0.00");

                pedidoAtual = null;

                Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
                sucesso.setTitle("Cancelamento bem-sucedido");
                sucesso.setHeaderText(null);
                sucesso.setContentText("O pedido foi cancelado com sucesso.");
                sucesso.show();

                irParaTelaLogin();

            } catch (Exception e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro");
                erro.setHeaderText(null);
                erro.setContentText("Erro: " + e.getMessage());
                erro.show();
            }
        }
    }


    private void atualizarTotal() {
        double total = 0.0;
        for (ItemPedido item : tableView.getItems()) {
            total = item.getValorItemPedido();
        }
        labelValorTotal.setText("R$ " + String.format("%.2f", total));
    }

}
