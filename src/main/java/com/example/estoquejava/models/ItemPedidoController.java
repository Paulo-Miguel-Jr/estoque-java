package com.example.estoquejava.models;

import com.example.estoquejava.repository.ItemPedidoRepositorio;

public class ItemPedidoController {

    private ItemPedidoRepositorio Itens;

    public ItemPedidoController(){
        this.Itens = new ItemPedidoRepositorio();
    }

    public void inserirItemPedido(ItemPedido item){
        Itens.InserirItemPedido(item);
    }

    public void removerItemPedido(ItemPedido item){
        Itens.RemoverItemPedido(item.getIdProduto(), item.getQuantidade());
    }

    public void alterarItemPedido(ItemPedido item, int modificacao){
        Itens.ModificarItemPedido(item.getIdProduto(), item.getQuantidade(), modificacao);
    }

    public ItemPedido buscarItemPedido(int id, int quantidade){
        return Itens.BuscarItemPedido(id, quantidade);
    }

    public int getQuantItensPorId(int id){
        return Itens.getQuantItensPedidos(id);
    }

    public int getQuantProdutosPorId(int id){
        return Itens.getQuantProdutosVendidos(id);
    }

    public ItemPedido[] getListaItemPedido(){
        return Itens.ListarItensPedidos();
    }
}
