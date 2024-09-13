package com.example.estoquejava.models;

import com.example.estoquejava.repository.ItemPedidoRepositorio;

public class ItemPedidoController {

    private ItemPedidoRepositorio itens;

    public ItemPedidoController() {
        this.itens = new ItemPedidoRepositorio();
    }

    public void adicionarItemPedido(ItemPedido item) {
        if (item != null) {
            itens.adicionarItemPedido(item);
        } else {
            throw new IllegalArgumentException("ItemPedido não pode ser vazio");
        }
    }

    public void removerItemPedido(int idItem) {
        if (idItem > 0) {
            itens.removerItemPedido(idItem);
        } else {
            throw new IllegalArgumentException("ID deve ser positivo.");
        }
    }

    public ItemPedido buscarItemPedido(int idItem) {
        if (idItem > 0) {
            return itens.buscarItemPedido(idItem);
        } else {
            throw new IllegalArgumentException("ID deve ser positivo.");
        }
    }

    public void listarItensPedido() {
        itens.listarItensPedidos();
    }
}
