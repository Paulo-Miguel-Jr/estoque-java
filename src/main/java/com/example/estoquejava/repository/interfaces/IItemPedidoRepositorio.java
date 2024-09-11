package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;

public interface IItemPedidoRepositorio {

    void adicionarItemPedido(ItemPedido novoItemPedido);

    void removerItemPedido(int idItem);

    ItemPedido buscarItemPedido(int idItem);

    void listarItensPedidos();

}

