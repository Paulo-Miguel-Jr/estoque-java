package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;

public interface IItemPedidoRepositorio {

    void adicionarItem(ItemPedido novoItemPedido);

    void removerItemPedido(int idItem);

   // void ModificarItemPedido(int id, int quantidade, int modificacao);

  //  int getQuantItensPedidos(int id);

 //   int getQuantProdutosVendidos(int id);

    ItemPedido buscarItemPedido(int idItem);

    void listarItensPedidos();

}

