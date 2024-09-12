package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.repository.PedidoRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

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
    private ListView<String> listViewItensPedido;

    private PedidoController pedidoController;
    private Pedido pedidoFinalizado;


    @FXML
    public void setarLabel() {
        double valorTotal = pedidoFinalizado.calcularValorTotal();
        labelValorFinalizado.setText(String.format("%.2f", valorTotal));
    }

    @FXML
    public void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @FXML
    public void iniciarOutroPedido() {
        irParaTelaPrincipal();
    }

    @FXML
    public void verPedido() {
        if (pedidoFinalizado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Pedido Finalizado");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum pedido foi finalizado recentemente.");
            alert.showAndWait();
            return;
        }

        try {
            List<ItemPedido> itens = List.of(pedidoFinalizado.getItensPedido());
            listViewItensPedido.getItems().clear();

            if (itens == null || itens.isEmpty()) {
                listViewItensPedido.getItems().add("Nenhum item encontrado no pedido.");
            } else {
                for (ItemPedido item : itens) {
                    listViewItensPedido.getItems().add(
                            "Produto: " + item.getProduto().getNome() +
                                    " - Quantidade: " + item.getQuantidade() +
                                    " - Valor Unitário: " + item.getProduto().getPreco()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao exibir itens do pedido: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void cancelarPedidoFinalizado() {
        if (pedidoFinalizado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Pedido para Cancelar");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum pedido foi finalizado recentemente para cancelar.");
            alert.showAndWait();
            return;
        }

        try {
            pedidoController.cancelarPedido(pedidoFinalizado.getIdPedido());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido Cancelado");
            alert.setHeaderText(null);
            alert.setContentText("O pedido com ID " + pedidoFinalizado.getIdPedido() + " foi cancelado com sucesso.");
            alert.showAndWait();

            pedidoFinalizado.setStatus(StatusPedido.CANCELADO);
            verPedido();
        } catch (PedNaoEncontException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pedido Não Encontrado");
            alert.setHeaderText(null);
            alert.setContentText("Pedido não encontrado com o ID fornecido.");
            alert.showAndWait();
        } catch (InvalidPedidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pedido Não Cancelável");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao cancelar pedido: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
