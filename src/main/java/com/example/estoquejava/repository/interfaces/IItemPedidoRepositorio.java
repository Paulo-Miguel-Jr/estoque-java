package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.exceptions.ItemPedNaoEncontException;
import com.example.estoquejava.models.exceptions.ItemPedidoRepCheioException;

public interface IItemPedidoRepositorio {

    void adicionarItemPedido(ItemPedido novoItemPedido) throws ItemPedidoRepCheioException;

    void removerItemPedido(ItemPedido itemPedido) throws ItemPedNaoEncontException;

    ItemPedido buscarItemPedido(int idItem) throws ItemPedNaoEncontException;

    void listarItensPedidos();

    void atualizarItemPedido(ItemPedido itemAtualizado) throws ItemPedNaoEncontException;

}

