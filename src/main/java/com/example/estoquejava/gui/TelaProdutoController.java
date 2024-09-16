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
    private Button adcQuantidadeButton;

    @FXML
    private TextField adcQuantidadeTextField;

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
    private TextField idProdutoTextField;

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
        // Inicializar o repositório usando o padrão Singleton
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

            //Salvar os dados no arquivo
            produtoRepositorio.salvarArquivo();

            //Limpar os campos após adicionar
            limparCampos();
            exibirAlerta("Sucesso", "Produto adicionado com sucesso!", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Por favor, insira valores numéricos válidos!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void adicionarQuantidade(ActionEvent event) {
        try {
            //ID do produto do campo de texto
            int idProduto = Integer.parseInt(idProdutoTextField.getText().trim());
            System.out.println(idProduto);
            //quantidade a ser adicionada do campo de texto
            int quantidadeAdicional = Integer.parseInt(adcQuantidadeTextField.getText().trim());
            System.out.println(quantidadeAdicional);
            if (quantidadeAdicional <= 0) {
                exibirAlerta("Erro", "A quantidade deve ser maior que zero.", Alert.AlertType.ERROR);
                return;
            }

            Produto produto = produtoRepositorio.obterProdutoPorId(idProduto);

            if (produto != null) {
                //atualiz a quantidade do produto
                double novaQuantidade = produto.getQuantidade() + (double)quantidadeAdicional;
                produto.setQuantidade(novaQuantidade);

                //atualiza o repositório com a nova quantidade
                produtoRepositorio.atualizarProduto(produto);
                produtoRepositorio.salvarArquivo();

                exibirAlerta("Sucesso", "Quantidade adicionada com sucesso ao produto " + produto.getNome() + ".", Alert.AlertType.INFORMATION);

                //Limpa os campos de texto
                idProdutoTextField.clear();
                quantidadeTextField.clear();
            } else {
                exibirAlerta("Erro", "Produto não encontrado com o ID especificado.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            exibirAlerta("Erro", "Por favor, insira um ID e uma quantidade válidos.", Alert.AlertType.ERROR);
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



