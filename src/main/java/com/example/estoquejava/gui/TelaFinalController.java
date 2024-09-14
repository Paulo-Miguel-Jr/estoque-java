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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TelaFinalController implements Initializable {
    @FXML
    private Button buttonVer, buttonCancelar, buttonOutroPedido;;

    @FXML
    private ImageView image;

    @FXML
    private Label labelValorFinalizado, labelFinalizado;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ListView<String> listViewItensPedido;

    private PedidoController pedidoController;
    private Pedido pedidoFinalizado;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedidoController = new PedidoController();
        if (pedidoFinalizado != null) {
            setarLabel();
        }
    }


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
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Pedido para Cancelar", "Nenhum pedido foi finalizado recentemente para cancelar.");
            return;
        }

        // Confirmação de cancelamento
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação de Cancelamento");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Você tem certeza que deseja cancelar o pedido finalizado?");

        Optional<ButtonType> result = confirmacao.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // Atualiza o status do pedido para CANCELADO
                pedidoFinalizado.setStatus(StatusPedido.CANCELADO);

                // Atualiza o repositório com o status cancelado
                pedidoController.atualizarPedido(pedidoFinalizado); // Isso deve garantir que o pedido no repositório também esteja atualizado

                // Exibe mensagem de sucesso
                mostrarAlerta(Alert.AlertType.INFORMATION, "Pedido Cancelado", "O pedido com ID " + pedidoFinalizado.getIdPedido() + " foi cancelado com sucesso.");

                // Atualiza a interface para refletir o cancelamento
                listViewItensPedido.getItems().clear(); // Limpa a lista de itens do pedido
                labelValorFinalizado.setText("R$ 0.00"); // Reseta o valor total do pedido
                pedidoFinalizado = null; // Reseta o pedido finalizado, já que ele foi cancelado

            } catch (PedNaoEncontException e) {
                mostrarAlerta(Alert.AlertType.WARNING, "Pedido Não Encontrado", "Pedido não encontrado com o ID fornecido.");
            } catch (InvalidPedidoException e) {
                mostrarAlerta(Alert.AlertType.WARNING, "Pedido Não Cancelável", e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta(Alert.AlertType.ERROR, "Erro", "Erro ao cancelar pedido: " + e.getMessage());
            }
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

}
