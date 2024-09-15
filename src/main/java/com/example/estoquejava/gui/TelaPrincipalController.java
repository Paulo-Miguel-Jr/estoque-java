package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.models.Produto;
import com.example.estoquejava.models.ItemPedidoController;
import com.example.estoquejava.repository.ProdutoRepositorio;
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
    private Label labelQuantidade;

    @FXML
    private Button buttonAdcProd, buttonRmvProd, buttonAdcCart, irCarrinho, atualizarButton;

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
    private ProdutoRepositorio produtoRepositorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        produtoRepositorio = ProdutoRepositorio.getInstance();

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

    @FXML
    private void carregarTableViewProduto() {

        observableListProduto.clear(); //limpa a lista atual de produtos
        Produto[] produtos = produtoRepositorio.listarTodos(); // Obtém produtos do arquivo
        observableListProduto.addAll(produtos); // Adiciona os produtos na lista
        tabela.setItems(observableListProduto); // Atualiza a tabela
    }

    public void atualizarTableView() {

        observableListProduto.clear(); //limpa a lista atual de produtos
        Produto[] produtos = produtoRepositorio.listarTodos(); // Obtém produtos do arquivo
        observableListProduto.addAll(produtos); // Adiciona os produtos na lista
        tabela.setItems(observableListProduto); // Atualiza a tabela
    }

    @FXML
    private void adicionarItem(ActionEvent event) {
        Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();
        if (produtoSelecionado != null) {
            double quantidadeSelecionada = Double.parseDouble(comboBox.getSelectionModel().getSelectedItem());
            ItemPedido itemPedido = new ItemPedido(produtoSelecionado);
            itemPedido.setQuantidade(quantidadeSelecionada);
            pedidoAtual.adicionarItemPedido(itemPedido); //adiciona o item ao pedido

            produtoSelecionado.setQuantidade(produtoSelecionado.getQuantidade() - quantidadeSelecionada);

            // Atualiza o repositório com a nova quantidade
            produtoRepositorio.atualizarProduto(produtoSelecionado);
            produtoRepositorio.salvarArquivo();

            itemPedidoController.adicionarItemPedido(itemPedido); //adiciona o item no repositório

            pedidoController.criarPedido(pedidoAtual); //atualizar o pedido no sistema

            carregarTableViewProduto();

            ScreenManager sm = ScreenManager.getInstance();
            TelaPedidoController telaPedidoController = sm.getTelaPedidoController();
            telaPedidoController.setPedidoAtual(pedidoAtual);
            sm.changeScreen("TelaPedido.fxml", "TelaPedido");
        }
    }

    @FXML
    private void removerProduto(ActionEvent event) {
        Produto produtoSelecionado = tabela.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            boolean removido = produtoRepositorio.removerProdutoPorId(produtoSelecionado.getId()); // Remove o produto do repositório

            if (removido) {
                carregarTableViewProduto(); // Atualiza a tabela após a remoção
                produtoRepositorio.salvarArquivo(); // Salva as mudanças no arquivo

                exibirAlerta(Alert.AlertType.INFORMATION, "Produto Removido", "O produto foi removido com sucesso.");
            } else {
                exibirAlerta(Alert.AlertType.ERROR, "Erro ao Remover", "Ocorreu um erro ao tentar remover o produto.");
            }
        } else {
            exibirAlerta(Alert.AlertType.WARNING, "Nenhum Produto Selecionado", "Por favor, selecione um produto para remover.");
        }
    }

    private void exibirAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void irParaTela(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        TelaPedidoController telaPedidoController = sm.getTelaPedidoController();
        telaPedidoController.setPedidoAtual(pedidoAtual);
        sm.changeScreen("TelaPedido.fxml", "TelaPedido");
    }

    @FXML
    private void irParaTelaProduto(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaProduto.fxml", "TelaProduto");
    }
}
