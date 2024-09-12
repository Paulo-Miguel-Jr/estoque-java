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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        colunaItem.setCellValueFactory(new PropertyValueFactory<>("item"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    @FXML
    public void setarLabel() {
        double valorTotal = pedidoAtual.calcularValorTotal();
        labelValorTotal.setText(String.format("%.2f", valorTotal));
    }


    @FXML
    public void irParaTelaPrincipal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }


    @FXML
    public void irParaTelaFinal() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaFinal.fxml", "TelaFinal");
    }

    @FXML
    public void finalizarPedido() {
        if (pedidoAtual == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Pedido para Finalizar");
            alert.setHeaderText(null);
            alert.setContentText("Nenhum pedido foi iniciado ou está pendente para finalizar.");
            alert.showAndWait();
            return;
        }

        try {
            pedidoController.processarVenda(pedidoAtual.getIdPedido());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido Finalizado");
            alert.setHeaderText(null);
            alert.setContentText("O pedido com ID " + pedidoAtual.getIdPedido() + " foi finalizado com sucesso.");
            alert.showAndWait();


            pedidoAtual.setStatus(StatusPedido.PROCESSADO);
            pedidoRepositorio.atualizarPedido(pedidoAtual);

            irParaTelaFinal();

        } catch (PedNaoEncontException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pedido Não Encontrado");
            alert.setHeaderText(null);
            alert.setContentText("Pedido não encontrado com o ID fornecido.");
            alert.showAndWait();
        } catch (InvalidPedidoException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Pedido Não Pode Ser Finalizado");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Erro ao finalizar pedido: " + e.getMessage());
            alert.showAndWait();
        }
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
                // Simula o cancelamento do pedido
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

                irParaTelaPrincipal();

            } catch (Exception e) {
                Alert erro = new Alert(Alert.AlertType.ERROR);
                erro.setTitle("Erro");
                erro.setHeaderText(null);
                erro.setContentText("Erro: " + e.getMessage());
                erro.show();
            }
        }
    }



}
