package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;

public interface ItemPedidoRepositorioInter {

    int getTamanho();

    void AdicionarItemPedido(ItemPedido item);

    void RemoverItemPedido(ItemPedido item);

    int buscaBinaria(ItemPedido item, int inicio, int fim);

    int getItemPedido(ItemPedido item);

    String toString();
}

