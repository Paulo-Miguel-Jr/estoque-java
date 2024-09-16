package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.*;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.repository.PedidoRepositorio;
import com.example.estoquejava.repository.ProdutoRepositorio;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private Button adicionarItem, criarPedido, cancelarPedido, buttonRemover;

    @FXML
    private ImageView image;

    @FXML
    private Label labelTotal, labelValorTotal, labelCarrinho;

    @FXML
    private TableView<ItemPedido> tableView;

    @FXML
    private TableColumn<ItemPedido, String> colunaItem;

    @FXML
    private TableColumn<ItemPedido, String> colunaPreco;

    @FXML
    private TableColumn<ItemPedido, String> colunaQuantidade;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane anchor1, anchor2, anchorInScroll;

    @FXML
    private SplitPane split;

    private ItemPedidoController itemPedidoController;
    private ObservableList<ItemPedido> listaItens;
    private Pedido pedidoAtual;
    private PedidoController pedidoController;
    private PedidoRepositorio pedidoRepositorio;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedidoRepositorio = PedidoRepositorio.getInstance();

        configurarColunas();
        listaItens = FXCollections.observableArrayList();
        itemPedidoController = new ItemPedidoController();
        pedidoController = new PedidoController();
    }

    private void configurarColunas() {
        colunaItem.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                return new SimpleStringProperty(item.getNomeProduto());
            } else {
                return new SimpleStringProperty("");
            }
        });

        colunaQuantidade.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                int quantidadeComoInt = (int) item.getQuantidade();
                return new SimpleStringProperty(String.valueOf(quantidadeComoInt));
            } else {
                return new SimpleStringProperty("");
            }
        });

        colunaPreco.setCellValueFactory(cellData -> {
            ItemPedido item = cellData.getValue();
            if (item != null) {
                return new SimpleStringProperty(String.format("%.2f", item.getPrecoProduto()));
            } else {
                return new SimpleStringProperty("");
            }
        });

    }


    public void setPedidoAtual(Pedido pedido) {
        this.pedidoAtual = pedido;
        atualizarInterfaceComPedido(pedido);
    }

    private void atualizarInterfaceComPedido(Pedido pedido) {
        atualizarTableViewItens();
        setarLabel();
    }

    private void atualizarTableViewItens() {
        listaItens.clear();
        if (pedidoAtual != null) {
            listaItens.addAll(pedidoAtual.getItens()); // adc os itens do pedido à lista
            tableView.setItems(listaItens); // atualiza a tabela
        }
    }

    @FXML
    public void setarLabel() {
        if (pedidoAtual != null) {
            double valorTotal = pedidoAtual.calcularValorTotal();
            labelValorTotal.setText(String.format("R$ %.2f", valorTotal));
        } else {
            labelValorTotal.setText("R$ 0.00");
        }
    }

    @FXML
    public void verFinal() {
        mudarTela("TelaFinal.fxml");
    }

    @FXML
    public void finalizarPedido() {
        if (pedidoAtual == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Pedido para Finalizar", "Nenhum pedido foi iniciado ou está pendente para finalizar.");
            return;
        }

        try {
            pedidoController.processarVenda(pedidoAtual.getIdPedido());
            pedidoAtual.setStatus(StatusPedido.PROCESSADO);
            pedidoController.atualizarPedido(pedidoAtual);
            pedidoRepositorio.atualizarPedido(pedidoAtual);


            pedidoRepositorio.salvarArquivo();

            mostrarAlerta(Alert.AlertType.INFORMATION, "Pedido Finalizado", "O pedido com ID " + pedidoAtual.getIdPedido() + " foi finalizado com sucesso.");

            listaItens.clear();
            //passa o pedido para a TelaFinal
            ScreenManager sm = ScreenManager.getInstance();
            TelaFinalController telaFinalController = sm.getTelaFinalController();
            telaFinalController.setPedidoAtual(pedidoAtual);

            verFinal();
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Erro ao Finalizar Pedido", "Erro ao finalizar pedido: " + e.getMessage());
        }
    }

    @FXML
    public void adicionarItem() {
        mudarTela("TelaPrincipal.fxml");
    }


    @FXML
    private void removerItemPedido(ActionEvent event) {
        ItemPedido itemSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (itemSelecionado != null) {
            Produto produto = itemSelecionado.getProduto();
            double quantidadeRemovida = itemSelecionado.getQuantidade();

            ProdutoRepositorio produtoRepositorio = ProdutoRepositorio.getInstance();
            Produto produtoNoRepositorio = produtoRepositorio.obterProdutoPorId(produto.getId());

            if (produtoNoRepositorio != null) {
                produtoNoRepositorio.setQuantidade(produtoNoRepositorio.getQuantidade() + quantidadeRemovida);

                produtoRepositorio.atualizarProduto(produtoNoRepositorio);
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Produto não encontrado no repositório.");
                return;
            }

            pedidoAtual.removerItemPedido(itemSelecionado);
            pedidoController.atualizarPedido(pedidoAtual);
            tableView.setItems(FXCollections.observableArrayList(pedidoAtual.getItens()));
            setarLabel();

            mostrarAlerta(Alert.AlertType.INFORMATION, "Item Removido", "O item foi removido com sucesso.");
        } else {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Item Selecionado", "Por favor, selecione um item para remover.");
        }
    }

    @FXML
    public void cancelarPedido() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Cancelamento");
        alert.setHeaderText("Você realmente deseja cancelar o pedido?");
        alert.setContentText("O pedido será cancelado e os itens serão devolvidos ao estoque.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ProdutoRepositorio produtoRepositorio = ProdutoRepositorio.getInstance();

            // percorre os itens exibidos na tabela
            ObservableList<ItemPedido> itensTableView = tableView.getItems();
            for (ItemPedido item : itensTableView) {
                if (item != null && item.getProduto() != null) {
                    Produto produto = item.getProduto();
                    double quantidadeRemovida = item.getQuantidade();

                    // recupera o produto do repositório
                    Produto produtoNoRepositorio = produtoRepositorio.obterProdutoPorId(produto.getId());

                    if (produtoNoRepositorio != null) {
                        produtoNoRepositorio.setQuantidade(produtoNoRepositorio.getQuantidade() + quantidadeRemovida);

                        produtoRepositorio.atualizarProduto(produtoNoRepositorio);
                    } else {
                        mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Produto não encontrado no repositório: " + produto.getNome());
                    }
                }
            }

            pedidoAtual.setStatus(StatusPedido.CANCELADO);
            pedidoAtual.limparItens();

            pedidoController.atualizarPedido(pedidoAtual);

            // limpa a tabela e atualiza o valor
            tableView.setItems(FXCollections.observableArrayList());
            labelValorTotal.setText("R$ 0.00");

            mostrarAlerta(Alert.AlertType.INFORMATION, "Pedido Cancelado", "O pedido foi cancelado e os itens foram devolvidos ao estoque.");

            pedidoAtual = null; // reseta o pedido atual
        }
    }

    private void cancelarPedidoConfirmado() {
        tableView.getItems().clear();
        labelValorTotal.setText("R$ 0.00");
        pedidoAtual = null;
        mostrarAlerta(Alert.AlertType.INFORMATION, "Cancelamento Bem-Sucedido", "O pedido foi cancelado com sucesso.");
        mudarTela("TelaPrincipal.fxml");
    }

    private void mudarTela(String telaFXML) {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen(telaFXML, telaFXML.replace(".fxml", ""));
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }


}
