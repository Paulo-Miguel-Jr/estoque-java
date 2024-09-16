package com.example.estoquejava.gui;

import com.example.estoquejava.ScreenManager;
import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.Produto;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.PedidoRepositorio;
import com.example.estoquejava.repository.ProdutoRepositorio;
import com.example.estoquejava.repository.UsuarioRepositorio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

public class TelaAdminController {

    @FXML
    private Button buttonvoltar;

    @FXML
    private ImageView image;

    @FXML
    private Button btnGerarRelatorioUsuarios;
    @FXML
    private Button btnGerarRelatorioProdutos;
    @FXML
    private Button btnGerarRelatorioPedidos;

    private final UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstance();
    private final ProdutoRepositorio produtoRepositorio = ProdutoRepositorio.getInstance();
    private final PedidoRepositorio pedidoRepositorio = PedidoRepositorio.getInstance();

    @FXML
    public void initialize() {
        btnGerarRelatorioUsuarios.setOnAction(event -> gerarRelatorioUsuariosPDF());
        btnGerarRelatorioProdutos.setOnAction(event -> gerarRelatorioProdutosPDF());
        btnGerarRelatorioPedidos.setOnAction(event -> gerarRelatorioPedidosPDF());
    }

    @FXML
    public void voltarLogin() {
        ScreenManager sm = ScreenManager.getInstance();
        sm.changeScreen("TelaLogin.fxml", "TelaLogin");

    }

    private void gerarRelatorioUsuariosPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório de Usuários");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));

        Stage stage = (Stage) btnGerarRelatorioUsuarios.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                criarRelatorioUsuariosPDF(file);
                mostrarAlerta("Sucesso", "Relatório de usuários gerado com sucesso.");
            } catch (FileNotFoundException | DocumentException e) {
                mostrarAlerta("Erro", "Erro ao gerar o relatório de usuários.");
                e.printStackTrace();
            }
        }
    }

    private void gerarRelatorioProdutosPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório de Produtos");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));

        Stage stage = (Stage) btnGerarRelatorioProdutos.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                criarRelatorioProdutosPDF(file);
                mostrarAlerta("Sucesso", "Relatório de produtos gerado com sucesso.");
            } catch (FileNotFoundException | DocumentException e) {
                mostrarAlerta("Erro", "Erro ao gerar o relatório de produtos.");
                e.printStackTrace();
            }
        }
    }

    private void gerarRelatorioPedidosPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório de Pedidos");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));

        Stage stage = (Stage) btnGerarRelatorioPedidos.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try {
                criarRelatorioPedidosPDF(file);
                mostrarAlerta("Sucesso", "Relatório de pedidos gerado com sucesso.");
            } catch (FileNotFoundException | DocumentException e) {
                mostrarAlerta("Erro", "Erro ao gerar o relatório de pedidos.");
                e.printStackTrace();
            }
        }
    }

    private void criarRelatorioUsuariosPDF(File file) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Relatório de Usuários\n\n"));
        Usuario[] usuarios = usuarioRepositorio.listarUsuarios();
        if (usuarios.length == 0) {
            document.add(new Paragraph("Nenhum usuário registrado."));
        } else {
            for (Usuario usuario : usuarios) {
                document.add(new Paragraph("ID: " + usuario.getId()));
                document.add(new Paragraph("Nome: " + usuario.getNome()));
                document.add(new Paragraph("Senha: " + usuario.getSenha()));
                document.add(new Paragraph("-------------------------------\n"));
            }
        }
        document.close();
    }

    private void criarRelatorioProdutosPDF(File file) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Relatório de Produtos\n\n"));
        Produto[] produtos = produtoRepositorio.listarTodos();
        if (produtos.length == 0) {
            document.add(new Paragraph("Nenhum produto registrado."));
        } else {
            for (Produto produto : produtos) {
                document.add(new Paragraph("ID: " + produto.getId()));
                document.add(new Paragraph("Nome: " + produto.getNome()));
                document.add(new Paragraph("Preço: " + produto.getPreco()));
                document.add(new Paragraph("Quantidade: " + produto.getQuantidade()));
                document.add(new Paragraph("Unidade de medida: " + produto.getUnidadeDeMedida()));
                document.add(new Paragraph("Estoque minimo: " + produto.getEstoqueMinimo()));
                document.add(new Paragraph("-------------------------------\n"));
            }
        }
        document.close();
    }

    private void criarRelatorioPedidosPDF(File file) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        document.add(new Paragraph("Relatório de Pedidos\n\n"));

        Pedido[] pedidos = pedidoRepositorio.listarPedidos();
        if (pedidos.length == 0) {
            document.add(new Paragraph("Nenhum pedido registrado."));
        } else {
            //adicionei um conjunto para armazenar os IDs dos pedidos adicionados
            Set<Integer> pedidosAdicionados = new HashSet<>();

            for (Pedido pedido : pedidos) {
                if (!pedidosAdicionados.contains(pedido.getIdPedido())) {
                    document.add(new Paragraph("ID do Pedido: " + pedido.getIdPedido()));
                    document.add(new Paragraph("Status do Pedido: " + pedido.getStatus()));
                    document.add(new Paragraph("Itens do Pedido:"));

                    for (ItemPedido item : pedido.getItensPedido()) {
                        if (item != null) {
                            document.add(new Paragraph("  - Produto ID: " + item.getProduto().getId()));
                            document.add(new Paragraph("    Nome: " + item.getProduto().getNome()));
                            document.add(new Paragraph("    Quantidade: " + item.getQuantidade()));
                            document.add(new Paragraph("    Preço Unitário: R$" + item.getProduto().getPreco()));
                            document.add(new Paragraph("    Estoque Mínimo: " + item.getProduto().getEstoqueMinimo()));
                        }
                    }
                    document.add(new Paragraph("Valor Total: R$" + pedido.getValorTotal()));
                    document.add(new Paragraph("-------------------------------\n"));

                    pedidosAdicionados.add(pedido.getIdPedido());
                }
            }
        }
        document.close();
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}