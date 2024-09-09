package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;

public interface IItemPedidoRepositorio {

    void InserirItemPedido(ItemPedido item);

    void RemoverItemPedido(int id, int quantidade);

    void ModificarItemPedido(int id, int quantidade, int modificacao);

    int getQuantItensPedidos(int id);

    int getQuantProdutosVendidos(int id);

    ItemPedido BuscarItemPedido(int id, int quantidade);

    ItemPedido[] ListarItensPedidos();

}

