package com.example.estoquejava.gui;

import com.example.estoquejava.models.Produto;
import com.example.estoquejava.repository.ProdutoRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class TelaProdutoController implements Initializable {

    @FXML
    private Button adicionarButton;

    @FXML
    private Label estoqueMinimoLabel;

    @FXML
    private TextField estoqueMinimoTextField;

    @FXML
    private Label nomeLabel;

    @FXML
    private TextField nomeTextField;

    @FXML
    private Label precoLabel;

    @FXML
    private TextField precoTextField;

    @FXML
    private Label quantidade;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private TextField unidadeDeMedidaTextField;

    @FXML
    private Label unidadeLabel;

    private ProdutoRepositorio produtoRepositorio;

    public TelaProdutoController() {
        // Inicializar o repositório usando o padrão Singleton
        produtoRepositorio = ProdutoRepositorio.getInstance();
    }

    @FXML
    public void initialize() {
        adicionarButton.setOnAction(event -> adicionarProduto());
    }

    private void adicionarProduto() {
        try {
            String nome = nomeTextField.getText();
            double preco = Double.parseDouble(precoTextField.getText());
            double quantidade = Double.parseDouble(quantidadeTextField.getText());
            String unidadeDeMedida = unidadeDeMedidaTextField.getText();
            double estoqueMinimo = Double.parseDouble(estoqueMinimoTextField.getText());

            //Gerar um novo ID baseado na quantidade de produtos já existentes
            int novoId = produtoRepositorio.getQuantidadeProdutos() + 1;

            // Criar novo produto
            Produto novoProduto = new Produto(nome, novoId, preco, quantidade, unidadeDeMedida, estoqueMinimo);

            // Adicionar o produto ao repositório (que cuida de validações)
            produtoRepositorio.adicionarProduto(novoProduto);

            // Salvar os dados no arquivo após a adição
            produtoRepositorio.salvarArquivo();

            // Limpar os campos após adicionar
            limparCampos();
            exibirAlerta("Sucesso", "Produto adicionado com sucesso!", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Por favor, insira valores numéricos válidos!", Alert.AlertType.ERROR);
        }
    }

    private void limparCampos() {
        nomeTextField.clear();
        precoTextField.clear();
        quantidadeTextField.clear();
        unidadeDeMedidaTextField.clear();
        estoqueMinimoTextField.clear();
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}



