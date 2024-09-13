package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.repository.PedidoRepositorio;

public class PedidoController {

    private PedidoRepositorio pedidoRepositorio;

    public PedidoController() {
        this.pedidoRepositorio = PedidoRepositorio.getInstance();
    }

    public int criarPedido(Pedido pedido) throws InvalidPedidoException {
        validarPedido(pedido);
        int novoId = gerarIdPedido();
        pedido.setIdPedido(novoId);
        pedidoRepositorio.adicionarPedido(pedido);
        return novoId;
    }

    public void removerPedido(int idPedido) throws PedNaoEncontException, InvalidPedidoException {
        Pedido pedido = pedidoRepositorio.procurarPedido(idPedido);
      //  if (pedido == null) {
      //      throw new PedNaoEncontException("Pedido não encontrado com o ID: " + idPedido);
     //   }
     //   if (pedido.getStatus() == StatusPedido.PROCESSADO || pedido.getStatus() == StatusPedido.CANCELADO) {
     //       throw new InvalidPedidoException("Não é possível remover um pedido que já foi processado ou cancelado.");
     //   }
        pedidoRepositorio.removerPedido(idPedido);
    }

    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException, InvalidPedidoException {
        validarPedido(pedido);
        Pedido pedidoExistente = pedidoRepositorio.procurarPedido(pedido.getIdPedido());
      //  if (pedidoExistente == null) {
      //      throw new PedNaoEncontException("Pedido não encontrado com o ID: " + pedido.getIdPedido());
     //   }
     //   if (pedidoExistente.getStatus() == StatusPedido.PROCESSADO || pedidoExistente.getStatus() == StatusPedido.CANCELADO) {
     //       throw new InvalidPedidoException("Não é possível atualizar um pedido que já foi processado ou cancelado.");
     //   }
        pedidoRepositorio.atualizarPedido(pedido);
    }

    public Pedido procurarPedido(int idPedido) throws PedNaoEncontException {
        Pedido pedido = pedidoRepositorio.procurarPedido(idPedido);
        if (pedido == null) {
            throw new PedNaoEncontException("Pedido não encontrado com o ID: " + idPedido);
        }
        return pedido;
    }

    public void processarVenda(int idPedido) throws PedNaoEncontException, InvalidPedidoException {
        Pedido pedido = procurarPedido(idPedido);
        //if (pedido.getStatus() != StatusPedido.PENDENTE) {
        //    throw new InvalidPedidoException("O pedido deve estar pendente para ser processado.");
       // }
        pedidoRepositorio.processarVenda(idPedido);
    }

    public void cancelarPedido(Pedido pedido) {
        if (pedido != null) {
            pedido.setStatus(StatusPedido.CANCELADO);
            pedido.limparItens(); // Remove todos os itens do pedido
            atualizarPedido(pedido);  // Atualiza o pedido no repositório
        }
    }

    public void adicionarItemAoPedido(int idPedido, ItemPedido item) throws PedNaoEncontException, LimiteItensAlcancadoException, InvalidPedidoException {
        Pedido pedido = procurarPedido(idPedido);
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new InvalidPedidoException("Somente pedidos pendentes podem ter itens adicionados.");
        }
        pedido.adicionarItemPedido(item);
        pedidoRepositorio.atualizarPedido(pedido);
    }

    private void validarPedido(Pedido pedido) throws InvalidPedidoException {
        if (pedido.getItensPedido() == null || pedido.getItensPedido().length == 0) {
            throw new InvalidPedidoException("O pedido deve conter pelo menos um item.");
        }
    }

    private int gerarIdPedido() {
        return pedidoRepositorio.gerarNovoId();
    }
}
