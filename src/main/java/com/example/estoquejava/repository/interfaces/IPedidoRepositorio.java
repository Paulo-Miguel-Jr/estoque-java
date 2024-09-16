package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;

public interface IPedidoRepositorio {

    void adicionarPedido(Pedido pedido) throws PedidoRepCheioException;

    void removerPedido(int idPedido) throws PedNaoEncontException;

    void atualizarPedido(Pedido pedido) throws PedNaoEncontException;

    Pedido[] listarPedidos();

    Pedido procurarPedido(int idPedido) throws PedNaoEncontException;

    void adicionarItemAoPedido (int idPedido, ItemPedido item) throws PedNaoEncontException;

    void processarVenda(int idPedido) throws PedNaoEncontException;

    void gerarRelatorioPedidoPendente();

    void gerarRelatorioPedidoCancelado();

    void gerarRelatorioPedidoProcessado();


}
