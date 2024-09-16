package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.Produto;
import com.example.estoquejava.models.enums.UnidadeDeMedida;
import com.example.estoquejava.repository.ProdutoRepositorio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.*;
import java.net.URL;
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
    private ComboBox<UnidadeDeMedida> unidadeDeMedidaComboBox;

    @FXML
    private Label unidadeLabel;

    @FXML
    private TextArea listaProdutosTextArea;

    @FXML
    private Button listarProdutosButton;

    @FXML
    private Button gerarRelatorioButton;

    @FXML
    private Button voltarButton;

    private ProdutoRepositorio produtoRepositorio;

    public TelaProdutoController() {
        produtoRepositorio = ProdutoRepositorio.getInstance();
    }

    @FXML
    private void adicionarProduto() {
        try {
            String nome = nomeTextField.getText().trim();
            if (nome.isEmpty()) {
                exibirAlerta("Erro", "O nome do produto não pode ser vazio.", Alert.AlertType.ERROR);
                return;
            }
            if (nome.length() < 3) {
                exibirAlerta("Erro", "O nome do produto deve ter pelo menos 3 caracteres.", Alert.AlertType.ERROR);
                return;
            }

            double preco = Double.parseDouble(precoTextField.getText().replace(",","."));
            if (preco <= 0) {
                exibirAlerta("Erro", "O preço deve ser um valor positivo.", Alert.AlertType.ERROR);
                return;
            }

            double quantidade = Double.parseDouble(quantidadeTextField.getText());
            if (quantidade < 0) {
                exibirAlerta("Erro", "A quantidade não pode ser negativa.", Alert.AlertType.ERROR);
                return;
            }

            UnidadeDeMedida unidadeDeMedida = unidadeDeMedidaComboBox.getValue();
            if (unidadeDeMedida == null) {
                exibirAlerta("Erro", "Por favor, selecione uma unidade de medida.", Alert.AlertType.ERROR);
                return;
            }

            double estoqueMinimo = Double.parseDouble(estoqueMinimoTextField.getText());
            if (estoqueMinimo < 0) {
                exibirAlerta("Erro", "O estoque mínimo não pode ser negativo.", Alert.AlertType.ERROR);
                return;
            }

            //Gerar um novo ID baseado na quantidade de produtos já existentes
            int novoId = produtoRepositorio.getQuantidadeProdutos() + 1;

            //criar novo produto
            Produto novoProduto = new Produto(nome, novoId, preco, quantidade, unidadeDeMedida, estoqueMinimo);
            produtoRepositorio.adicionarProduto(novoProduto);

            produtoRepositorio.salvarArquivo();

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
        unidadeDeMedidaComboBox.getSelectionModel().clearSelection();
        estoqueMinimoTextField.clear();
    }

    @FXML
    private void listarTodosProdutos() {
        Produto[] produtos = produtoRepositorio.listarTodos();  //Pega todos os produtos do repositório
        if (produtos.length == 0) {
            listaProdutosTextArea.setText("Nenhum produto cadastrado.");
        } else {
            StringBuilder builder = new StringBuilder();
            for (Produto produto : produtos) {
                builder.append("ID: ").append(produto.getId())
                        .append(", Nome: ").append(produto.getNome())
                        .append(", Preço: ").append(produto.getPreco())
                        .append(", Quantidade: ").append(produto.getQuantidade())
                        .append(", Unidade: ").append(produto.getUnidadeDeMedida())
                        .append(", Estoque Mínimo: ").append(produto.getEstoqueMinimo())
                        .append("\n");
            }
            listaProdutosTextArea.setText(builder.toString());  //Exibe os produtos na TextArea
        }
    }

    //Gerar o relatório de produtos em baixa e exibir no TextArea
    @FXML
    private void gerarRelatorioProdutosEmBaixa() {
        String relatorio = produtoRepositorio.gerarRelatorioProdutosEmBaixa();
        listaProdutosTextArea.setText(relatorio);
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    void irParaTelaPrincipal(ActionEvent event) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaPrincipal.fxml", "TelaPrincipal");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        unidadeDeMedidaComboBox.getItems().setAll(UnidadeDeMedida.values());
    }
}



