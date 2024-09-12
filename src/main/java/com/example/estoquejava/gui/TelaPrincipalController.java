package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
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
    private TableColumn<Produto, Double> colunaPreco;

    @FXML
    private TableColumn<Produto, String> colunaProduto;

    @FXML
    private TableColumn<Produto, Double> colunaQuantidade;

    @FXML
    private Button irCarrinho;

    @FXML
    private TableView<Produto> tabela;

    private List<Produto> listProduto = new ArrayList<>();

    private ObservableList<Produto> observableListProduto = FXCollections.observableArrayList();

    public ObservableList<Produto> getPersonData() {
        return observableListProduto;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarTableViewProduto();


    }

    public void carregarTableViewProduto() {
        //colunaProduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
        //colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        //colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        //Essa da baixo que esta funcionando pelo menos o nome.
        colunaProduto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
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
