package com.example.estoquejava.repository;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.ItemPedNaoEncontException;
import com.example.estoquejava.models.exceptions.ItemPedidoRepCheioException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.interfaces.IItemPedidoRepositorio;

public  class ItemPedidoRepositorio implements IItemPedidoRepositorio {

    private ItemPedido[] itemPedido;
    private int proximo;
    private static ItemPedidoRepositorio singletonPedRep;

    public ItemPedidoRepositorio (){
        this.itemPedido = new ItemPedido[100];
        this.proximo = 0;
    }

    public static ItemPedidoRepositorio getInstance(){
        if (singletonPedRep == null) {
            singletonPedRep = new ItemPedidoRepositorio();
        }
        return singletonPedRep;
    }

    public int getIdItemPedido(int idItem) {
        for (int i = 0; i < proximo; i++) {
            if (itemPedido[i].getIdItem() == idItem) {
                return i;
            }
        }
        return -1;
    }

    public void adicionarItemPedido(ItemPedido novoItemPedido) throws ItemPedidoRepCheioException {
        if (proximo < this.itemPedido.length) {
            this.itemPedido[proximo] = novoItemPedido;
            proximo++;
        } else {
            throw new ItemPedidoRepCheioException("Repositório cheio. Não é possível adicionar mais itens de pedido.");
        }
    }

    public void removerItemPedido(int idItem) throws ItemPedNaoEncontException {
        int indice = getIdItemPedido(idItem);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            // Move todos os elementos após o índice para uma posição anterior
            for (int i = indice; i < proximo - 1; i++) {
                itemPedido[i] = itemPedido[i + 1];
            }
            // Limpa o último elemento, que foi movido para frente
            itemPedido[proximo - 1] = null;
            proximo--;
        }
    }

    public ItemPedido buscarItemPedido(int idItem) throws ItemPedNaoEncontException {
        int indice = getIdItemPedido(idItem);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            return itemPedido[indice];
        }
    }

    public void listarItensPedidos() {
        for (int i = 0; i < proximo; i++) {
            if (itemPedido[i] != null) {
                System.out.println(itemPedido[i]);
            }
        }
    }
}
