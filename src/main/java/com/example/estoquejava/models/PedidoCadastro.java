package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.PedidoRepositorio;

public class PedidoCadastro {
    private PedidoRepositorio pedidoRepositorio;

    public PedidoCadastro(PedidoRepositorio pedidoRepositorio){
        this.pedidoRepositorio = PedidoRepositorio.getInstance();
    }

    public void criarPedido(Pedido pedido) throws PedidoRepCheioException {
        validarPedido(pedido);
        pedidoRepositorio.adicionarPedido(pedido);
    }

    public void removerPedido(int numero) throws PedNaoEncontException, InvalidPedidoException {
        Pedido pedido = pedidoRepositorio.procurarPedido(numero);
        if (pedido.getStatus() == StatusPedido.PROCESSADO || pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new InvalidPedidoException("Não é possível remover um pedido que já foi processado ou cancelado.");
        }
        pedidoRepositorio.removerPedido(numero);
    }

    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException, InvalidPedidoException {
        validarPedido(pedido);
        Pedido pedidoExistente = pedidoRepositorio.procurarPedido(pedido.getIdPedido());
        if (pedidoExistente.getStatus() == StatusPedido.PROCESSADO || pedidoExistente.getStatus() == StatusPedido.CANCELADO) {
            throw new InvalidPedidoException("Não é possível atualizar um pedido que já foi processado ou cancelado.");
        }
        pedidoRepositorio.atualizarPedido(pedido);
    }

    public void procurarPedido(int numero) throws PedNaoEncontException {
        pedidoRepositorio.procurarPedido(numero);
    }

    public void processarVenda(int numero) throws PedNaoEncontException, InvalidPedidoException {
        Pedido pedido = pedidoRepositorio.procurarPedido(numero);
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new InvalidPedidoException("O pedido deve estar pendente para ser processado.");
        }
        pedidoRepositorio.processarVenda(numero);
    }

    public void listarPedido() {
        pedidoRepositorio.listarPedidos();
    }

    private void validarPedido(Pedido pedido) throws InvalidPedidoException {
        if (pedido.getDataPedido() == null) {
            throw new InvalidPedidoException("A data do pedido não pode ser nula.");
        }
        if (pedido.getItensPedido() == null || pedido.getItensPedido().length == 0) {
            throw new InvalidPedidoException("O pedido deve conter pelo menos um item.");
        }
    }

    public void adicionarItemAoPedido(int idPedido, ItemPedido item) throws PedNaoEncontException, LimiteItensAlcancadoException, InvalidPedidoException {
        Pedido pedido = pedidoRepositorio.procurarPedido(idPedido);
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new InvalidPedidoException("Somente pedidos pendentes podem ter itens adicionados.");
        }
        pedido.adicionarItemPedido(item);
        pedidoRepositorio.atualizarPedido(pedido);
    }
}
