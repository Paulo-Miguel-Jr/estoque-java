package com.example.estoquejava.models;

import com.example.estoquejava.repository.ItemPedidoRepositorio;

public class ItemPedidoController {

    private ItemPedidoRepositorio itens;

    public ItemPedidoController(){
        this.itens = new ItemPedidoRepositorio();
    }

    public void adicionarItemPedido(ItemPedido item){
        if(item != null) {
            itens.adicionarItemPedido(item);
        }
        else{
            throw new IllegalArgumentException("ItemPedido não pode ser vazio");
        }
    }

    public void removerItemPedido(int idItem){
        if(idItem <= 0){
            itens.removerItemPedido(idItem);
        }
        else{
            throw new IllegalArgumentException("ID deve ser positivo. ");
        }
    }

    public ItemPedido buscarItemPedido(int idItem){
        if(idItem <=0){
            throw new IllegalArgumentException("ID deve ser positivo. ");
        }

        else{
            return itens.buscarItemPedido(idItem);
        }
    }

    public void listarItensPedido(){
        itens.listarItensPedidos();
    }

    public void modificarItemPedido(ItemPedido item, ItemPedido modificacao){
        if(item != null && modificacao != null){
            itens.modificarItemPedido(item, modificacao);
        }
        else{
            throw new IllegalArgumentException("Os elementos de entrada devem estar preenchidos. ");
        }
    }
}
