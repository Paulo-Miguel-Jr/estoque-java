package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.repository.UsuarioRepositorio;
import com.example.estoquejava.repository.PedidoRepositorio;

public class Fachada {

    private static Fachada instancia;

    private UsuarioController usuarioController;
    private PedidoController pedidoController;
    private ItemPedidoController itemPedidoController;


    private Fachada() {
        this.usuarioController = new UsuarioController();
        this.pedidoController = new PedidoController();
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    //métodos usuario===========================================================

    public void cadastrarUsuario(Usuario usuario) {
        usuarioController.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioController.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioController.buscarUsuarioPorNome(nome);
    }

    public void removerUsuario(int id) {
        usuarioController.removerUsuario(id);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioController.atualizarUsuario(usuario);
    }

    public Usuario[] listarUsuarios() {
        return usuarioController.listarUsuarios();
    }



    //métodos para a entidade Pedido===========================================================

    public void criarPedido(Pedido pedido) {
        pedidoController.criarPedido(pedido);
    }

    public void removerPedido(int idPedido) {
        pedidoController.removerPedido(idPedido);
    }

    public void atualizarPedido(Pedido pedido) {
        pedidoController.atualizarPedido(pedido);
    }

    public Pedido procurarPedido(int idPedido) {
        return pedidoController.procurarPedido(idPedido);
    }

    public void processarVenda(int idPedido) {
        pedidoController.processarVenda(idPedido);
    }

    public void listarPedidos() {
        pedidoController.listarPedido();
    }

    public void adicionarItemAoPedido(int idPedido, ItemPedido item) {pedidoController.adicionarItemAoPedido(idPedido, item );}

    public void cancelarPedido(int idPedido) {
        pedidoController.setStatus(StatusPedido.CANCELADO);
    }


    //métodos ItemPedido===========================================================

    public void criarItemPedido(ItemPedido item){itemPedidoController.inserirItemPedido(item);}

    public void deletarItemPedido(ItemPedido item){itemPedidoController.removerItemPedido(item);}

    public void atualizarItemPedido(ItemPedido item, int modificacao){itemPedidoController.alterarItemPedido(item, modificacao);}

    public ItemPedido procurarItemPedido(int id, int quantidade){return itemPedidoController.buscarItemPedido(id, quantidade);}

    public int getQuantItensVendidosPorId(int id){return itemPedidoController.getQuantItensPorId(id);}

    public int getQuantProdutosVendidosPorId(int id){return itemPedidoController.getQuantProdutosPorId(id);}

    public ItemPedido[] getListaItensPedidos(){return itemPedidoController.getListaItemPedido();}

}
