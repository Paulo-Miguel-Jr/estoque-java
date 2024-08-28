package com.example.estoquejava.repository;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.repository.interfaces.IItemPedidoRepositorio;

import java.util.ArrayList;

public  class ItemPedidoRepositorio implements IItemPedidoRepositorio {

    private final ArrayList<ItemPedido> ItemPedidoLista;

    public ItemPedidoRepositorio(ItemPedido itemPedido){
        this.ItemPedidoLista = new ArrayList<>();
        this.ItemPedidoLista.add(itemPedido);
    }

    ///Função para auxiliar as operações de busca e inserção.
    public int getTamanho(){
        return (this.ItemPedidoLista.size());
    }

    ///Função de inserir Itens Pedidos na lista.
    public void AdicionarItemPedido(ItemPedido item){
        int a = 0;
        this.ItemPedidoLista.add(item);

        ///Mantêm a lista ordenada por ID de forma crescente.
        for(int i = 0; i < getTamanho(); i++){
            if(item.getIdProduto() >= ItemPedidoLista.get(i).getIdProduto()){

                a = i;
                i = getTamanho();
            }
        }

        ///Mantêm os elementos com o mesmo ID ordenados por ordem crescente de quantidade de produtos.
        if(item.getIdProduto() == ItemPedidoLista.get(a).getIdProduto()){
            for (int i = a; i < getTamanho(); i++){
                if(ItemPedidoLista.get(i).getQuantidade() >= item.getQuantidade()){
                    a = i;
                    i = getTamanho();
                }
            }
        }

        ///Posiciona o Item que se deseja inserir no lugar correto.
        for(int i = getTamanho()-1; i > a; i--){
            this.ItemPedidoLista.set(i, ItemPedidoLista.get(i-1));
        }

        ItemPedidoLista.set(a, item);
    }

    ///Remove itens da lista.
    public void RemoverItemPedido(ItemPedido item){
        ItemPedidoLista.remove(item);
    }

    ///Função auxiliar para a operação de busca.
    public int buscaBinaria(ItemPedido item, int inicio, int fim){
        int aux = (inicio + fim)/2;
        int resultado = -1;

        if(aux == fim){
            if(ItemPedidoLista.get(aux).getIdProduto() == item.getIdProduto()){
                resultado = aux;
                return resultado;
            }
            else{
                return resultado;
            }
        }
        if(aux == inicio){
            if(ItemPedidoLista.get(aux).getIdProduto() == item.getIdProduto()){
                return resultado;
            }
        }
        if(ItemPedidoLista.get(aux).getIdProduto() < item.getIdProduto()){
            resultado = buscaBinaria(item, inicio, aux);
        }

        if(ItemPedidoLista.get(aux).getIdProduto() > item.getIdProduto()){
            resultado = buscaBinaria(item, aux, fim);
        }

        if(ItemPedidoLista.get(aux).getIdProduto() == item.getIdProduto() &&
                ItemPedidoLista.get(aux).getQuantidade() == item.getQuantidade()){
            resultado = aux;
        }

        return resultado;

    }

    ///Para facilitar a busca de um item na lista no resto do código fiz desse jeito.
    public int getItemPedido(ItemPedido item){

        ///Atribui os valores que serão necessários para a busca
        int inicio = 0;
        int fim = getTamanho()-1;
        int aux = fim/2;
        int resultado = -1;

        ///Onde de fato a busca é realizada.
        if(ItemPedidoLista.get(aux).getIdProduto() < item.getIdProduto()){
            resultado = buscaBinaria(item, inicio, aux);
        }

        if(ItemPedidoLista.get(aux).getIdProduto() > item.getIdProduto()){
            resultado = buscaBinaria(item, aux, fim);
        }

        if(ItemPedidoLista.get(aux).getIdProduto() == item.getIdProduto() &&
                ItemPedidoLista.get(aux).getQuantidade() == item.getQuantidade()){
            resultado = aux;
        }

        return resultado;

    }


    @Override
    public String toString(){
        String retornar = "";

        for(int i = 0; i < getTamanho(); i++){
            retornar += ItemPedidoLista.get(i).toString();
        }

        return retornar;
    }
}
