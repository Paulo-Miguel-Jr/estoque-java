package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.repository.PedidoRepositorio;
import javafx.collections.FXCollections;
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
    private Pedido pedidoAtual;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedidoController = new PedidoController();
        if (pedidoAtual != null) {
            setarLabel();
        }
    }


    @FXML
    public void setarLabel() {
        double valorTotal = pedidoAtual.calcularValorTotal();
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
        ScreenManager sm = ScreenManager.getInstance();
        TelaPedidoController telaPedidoController = sm.getTelaPedidoController();
        telaPedidoController.setPedidoAtual(pedidoAtual);
        sm.changeScreen("TelaVerPedido.fxml", "TelaVerPedido");
    }

    @FXML
    public void cancelarPedidoFinalizado() {
        if (pedidoAtual == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Nenhum Pedido para Cancelar", "Nenhum pedido foi finalizado recentemente para cancelar.");
            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacao.setTitle("Confirmação de Cancelamento");
        confirmacao.setHeaderText(null);
        confirmacao.setContentText("Você tem certeza que deseja cancelar o pedido finalizado?");

        Optional<ButtonType> result = confirmacao.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                pedidoAtual.setStatus(StatusPedido.CANCELADO);

                pedidoController.atualizarPedido(pedidoAtual);

                mostrarAlerta(Alert.AlertType.INFORMATION, "Pedido Cancelado", "O pedido com ID " + pedidoAtual.getIdPedido() + " foi cancelado com sucesso.");


                listViewItensPedido.getItems().clear();
                labelValorFinalizado.setText("R$ 0.00");
                pedidoAtual = null;

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
