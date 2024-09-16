package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.PedidoController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaFinalController implements Initializable {
    @FXML
    private Button buttonVer, buttonOutroPedido;;

    @FXML
    private ImageView image;

    @FXML
    private Label labelValorFinalizado, labelFinalizado;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ListView<ItemPedido> listViewItensPedido;

    private PedidoController pedidoController;
    private Pedido pedidoAtual;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pedidoController = new PedidoController();
        listViewItensPedido = new ListView<>();
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
        if (pedidoAtual != null) {
            pedidoAtual.limparItens(); // Limpa os itens do pedido atual para garantir que não acumulem
        }
        pedidoAtual = null; // Reseta o pedido atual
        irParaTelaPrincipal(); // Volta para a tela principal
    }

    @FXML
    public void verPedido() {
        ScreenManager sm = ScreenManager.getInstance();
        TelaVerPedidoController telaVerPedidoController = sm.getTelaVerPedidoController();
        telaVerPedidoController.setPedido(pedidoAtual);
        sm.changeScreen("TelaVerPedido.fxml", "Tela Ver Pedido");
    }



    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String conteudo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    public void setPedidoAtual(Pedido pedidoAtual) {
        this.pedidoAtual = pedidoAtual;
    }
}
