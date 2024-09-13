package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.repository.UsuarioRepositorio;
import com.example.estoquejava.repository.PedidoRepositorio;
import com.example.estoquejava.repository.ItemPedidoRepositorio;

public class Fachada {

    private static Fachada instancia;

    private UsuarioController usuarioController;
    private PedidoController pedidoController;

    private PedidoRepositorio pedidoRepositorio;
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
        pedidoRepositorio.listarPedidos();
    }

    public void adicionarItemAoPedido(int idPedido, ItemPedido item) {pedidoController.adicionarItemAoPedido(idPedido, item );}

    public void cancelarPedido(int idPedido) throws PedNaoEncontException {
        Pedido pedido = procurarPedido(idPedido);
        if (pedido != null && pedido.getStatus() == StatusPedido.PENDENTE) {
            pedido.setStatus(StatusPedido.CANCELADO);

        } else {
            throw new PedNaoEncontException("Pedido não encontrado com o número: " + idPedido);
        }
    }


    //métodos ItemPedido===========================================================

    public void adicionarItemPedido(ItemPedido item){
        itemPedidoController.adicionarItemPedido(item);
    };

    public void removerItemPedido(ItemPedido itemPedido){
        itemPedidoController.removerItemPedido(itemPedido);
    }

    public ItemPedido buscarItemPedido(int idItem){
        return itemPedidoController.buscarItemPedido(idItem);
    }

    public void listarItensPedido(){
        itemPedidoController.listarItensPedido();
    }

}
