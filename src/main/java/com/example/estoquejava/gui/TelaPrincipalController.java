package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.models.Produto;
import com.example.estoquejava.models.ItemPedidoController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane border;

    @FXML
    private Button buttonAdcProd, buttonRmvProd, buttonAdcCart, irCarrinho;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TableColumn<Produto, String> colunaPreco, colunaProduto, colunaQuantidade;

    @FXML
    private TableView<Produto> tabela;

    private ObservableList<Produto> observableListProduto = FXCollections.observableArrayList();
    private PedidoController pedidoController;
    private ItemPedidoController itemPedidoController;
    private Pedido pedidoAtual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configurarColunas();
        carregarTableViewProduto();

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        comboBox.getItems().clear();
                        for (int i = 1; i <= newValue.getQuantidade(); i++) {
                            comboBox.getItems().add(String.valueOf(i));
                        }
                        comboBox.getSelectionModel().selectFirst();
                    }
                });

        pedidoController = new PedidoController();
        itemPedidoController = new ItemPedidoController();
        pedidoAtual = new Pedido(); //cria novo ou adiciona existente
    }

    private void configurarColunas() {
        colunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaPreco.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getPreco())));
        colunaQuantidade.setCellValueFactory(cellData -> new SimpleStringProperty(String.format("%.2f", cellData.getValue().getQuantidade())));
    }

    private void carregarTableViewProduto() {
        Produto p1 = new Produto("Arroz", 1, 8.99, 20, "pacote", 5);
        Produto p2 = new Produto("Feijão", 2, 6.99, 30, "pacote", 10);

        observableListProduto.addAll(p1, p2);
        tabela.setItems(observableListProduto);
    }

    @FXML
    private void adicionarItem(ActionEvent event) {
        Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            ItemPedido itemPedido = new ItemPedido(produtoSelecionado);
            itemPedido.setQuantidade(Double.parseDouble(comboBox.getSelectionModel().getSelectedItem()));
            pedidoAtual.adicionarItemPedido(itemPedido); //adiciona o item ao pedido

            itemPedidoController.adicionarItemPedido(itemPedido); //adiciona o item no repositório

            pedidoController.criarPedido(pedidoAtual); //atualizar o pedido no sistema
            ScreenManager sm = ScreenManager.getInstance();
            TelaPedidoController telaPedidoController = sm.getTelaPedidoController();
            telaPedidoController.setPedidoAtual(pedidoAtual);
            sm.changeScreen("TelaPedido.fxml", "TelaPedido");
        }
    }

    @FXML
    private void irParaTela(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        TelaPedidoController telaPedidoController = sm.getTelaPedidoController();
        telaPedidoController.setPedidoAtual(pedidoAtual);
        sm.changeScreen("TelaPedido.fxml", "TelaPedido");
    }
}
