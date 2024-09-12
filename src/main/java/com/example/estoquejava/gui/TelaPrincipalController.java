package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Produto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane border;

    @FXML
    private Button buttonAdcProd;

    @FXML
    private Button buttonRmvProd;

    @FXML
    private ComboBox<String> comboBox;

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

    private List<Produto> listProduto = new ArrayList<>();

    private ObservableList<Produto> observableListProduto = FXCollections.observableArrayList();

    public ObservableList<Produto> getProdutoData() {
        return observableListProduto;
    }

    private List<ItemPedido> listItemPedido = new ArrayList<>();

    private ObservableList<ItemPedido> observableListItemPedido = FXCollections.observableArrayList();

    public ObservableList<ItemPedido> getItemPedidoData() {
        return observableListItemPedido;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarTableViewProduto();

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        // Limpar a ComboBox
                        comboBox.getItems().clear();
                        // Preenche a ComboBox com números de 1 até a quantidade do produto selecionado
                        for (int i = 1; i <= newValue.getQuantidade(); i++) {
                            comboBox.getItems().add(String.valueOf(i));
                        }
                        // Selecionar o primeiro valor da ComboBox como padrão
                        comboBox.getSelectionModel().selectFirst();
                    }

                });



    }

    // Carrega os dados para a tabela de produtos
    public void carregarTableViewProduto() {
        colunaPreco.setCellValueFactory(new PropertyValueFactory<Produto, String>("preco"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        colunaPreco.setCellValueFactory(cellData -> {
            String preco = String.format("%.2f", cellData.getValue().getPreco());
            return new SimpleStringProperty(preco);
        });
        colunaQuantidade.setCellValueFactory(cellData -> {
            String quantidade = String.format("%.2f", cellData.getValue().getQuantidade());
            return new SimpleStringProperty(quantidade);
        });

        //colunaProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //colunaPreco.setCellValueFactory(cellData -> new PropertyValueFactory<Produto, Double>(cellData.getValue().getPreco()));
        //colunaQuantidade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getQuantidade()));


        //teste sem arquivo, apagar depois.
        Produto p1 = new Produto("Arroz", 1, 8.99,
                20, "pacote", 5);
        Produto p2 = new Produto("Feijão", 2, 6.99,
                30, "pacote", 10);

        listProduto.add(p1);
        listProduto.add(p2);

        observableListProduto = FXCollections.observableArrayList(listProduto);

        tabela.setItems(observableListProduto);

    }

    public void selecionarItemTabela(Produto produto) {
        System.out.println("Produto Selecionado: " + produto.getNome());
    }

    private void adicionarProdutoCarrinho(Produto produto) {
        ItemPedido novoItemPedido = new ItemPedido(produto);

    }


    /*
    private void adicionarProduto() {
        String nome = tfNome.getText();
        int id = tfId.getText();
        double preco = tfPreco.getText();
        double quantidade = tfQuantidade.getText();
        String unidadeDeMedida = tfMedida.getText();
        double estoqueMinimo = tfMinimo.getText();

        if(!nome.isEmpty() && !unidadeDeMedida.isEmpty()) {
            Produto novoProduto = new Produto(nome, id, preco, quantidade, unidadeDeMedida, estoqueMinimo);
            observableListProduto.add(novoProduto);

            tfNome.clear();
            tfID.clear();
            tfPreco.clear();
            tfQuantidade.clear();
            tfMedida.clear();
            tfMinimo.clear();

        }

    }  */

    @FXML
    private void irParaTela(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPedido.fxml", "TelaPedido");
    }

}
