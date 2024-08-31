package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;

public interface IPedidoRepositorio {

    //trocar numero por idPedido
    void adicionarPedido(Pedido pedido) throws PedidoRepCheioException;//trocar por cadastrar

    void removerPedido(int numero) throws PedNaoEncontException;

    void atualizarPedido(Pedido pedido) throws PedNaoEncontException;

    void listarPedidos();

    Pedido procurarPedido(int numero) throws PedNaoEncontException;

    //void adicionarItemAoPedido (int idPedido, ItemPedido item) throws PedNaoEncontException;

}
