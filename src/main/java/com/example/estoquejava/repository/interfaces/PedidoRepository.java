package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;

public interface PedidoRepository {

    void adicionarPedido(Pedido pedido) throws PedidoRepCheioException;

    void removerPedido(int numero) throws PedNaoEncontException;

    void atualizarPedido(Pedido pedido) throws PedNaoEncontException;

    void listarPedidos();

    Pedido procurarPedido(int numero) throws PedNaoEncontException;

    void processarVenda(int numero) throws PedNaoEncontException;

    void listarHistoricoAlteracoes();
}
