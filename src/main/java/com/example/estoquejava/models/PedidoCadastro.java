package com.example.estoquejava.models;

import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.PedidoRepositorio;

public class PedidoCadastro {
    private PedidoRepositorio pedidoRepositorio;

    public PedidoCadastro(PedidoRepositorio pedidoRepositorio){
        this.pedidoRepositorio = PedidoRepositorio.getInstance();
    }

    public void criarPedido(Pedido pedido) throws PedidoRepCheioException {
        pedidoRepositorio.adicionarPedido(pedido);
    }

    public void removerPedido(int numero) throws PedNaoEncontException {
        pedidoRepositorio.removerPedido(numero);
    }

    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException {
        pedidoRepositorio.atualizarPedido(pedido);
    }

    public void procurarPedido(int numero) throws PedNaoEncontException {
        pedidoRepositorio.procurarPedido(numero);
    }

    public void processarVenda(int numero) throws PedNaoEncontException {
        pedidoRepositorio.processarVenda(numero);
    }

    public void listarPedido() {
        pedidoRepositorio.listarPedidos();
    }

    //public void adicionarItemAoPedido (int idPedido, ItemPedido item) throws PedNaoEncontException {}
}
