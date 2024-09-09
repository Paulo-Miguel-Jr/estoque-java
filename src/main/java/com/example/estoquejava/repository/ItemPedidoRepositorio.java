package com.example.estoquejava.repository;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.repository.interfaces.IItemPedidoRepositorio;

public  class ItemPedidoRepositorio implements IItemPedidoRepositorio {

    private ItemPedido[] Itens;
    private int tamanho;
    private int contador;
    private static ItemPedidoRepositorio singletonPedRep;

    public ItemPedidoRepositorio (){
        this.Itens = new ItemPedido[10];
        this.tamanho = 10;
        this.contador = 0;
    }

    public static ItemPedidoRepositorio getInstance(){
        if (singletonPedRep == null) {
            singletonPedRep = new ItemPedidoRepositorio();
        }
        return singletonPedRep;
    }


    private void AumentarLista(){
        ItemPedido[] aux = new ItemPedido[this.tamanho+10];

        for(int i = 0; i < tamanho; i++) {
           aux[i] = this.Itens[i];
        }

        this.Itens = aux;
        this.tamanho += 10;
    }


    private void DiminuirLista(){
        ItemPedido[] aux = new ItemPedido[tamanho-10];

        for(int i = 0; i < this.tamanho; i++){
            aux[i] = this.Itens[i];
        }

        this.Itens = aux;
        this.tamanho -= 10;
    }


    public void InserirItemPedido(ItemPedido item){
        if(!(contador<tamanho)){
            AumentarLista();
        }

        contador++;
        Itens[contador] = item;

    }


    public void RemoverItemPedido(int id, int quantidade){

        for(int i = 0; i <= contador; i++){
            if(this.Itens[i].getIdProduto() == id && this.Itens[i].getQuantidade() == quantidade){
                this.Itens[i] = this.Itens[contador];
                contador--;
                i = contador+1;
            }

            if(i == contador){
                throw new IllegalArgumentException("Item não existe na lista");
            }
        }

        if (tamanho - contador >= 20){
            DiminuirLista();
        }

    }


    public ItemPedido BuscarItemPedido(int id, int quantidade){

        VerificarItemPedido(id);

        for(int i = 0; i <= contador; i++){
            if(this.Itens[i].getIdProduto() == id
            && this.Itens[i].getQuantidade() == quantidade){
                return this.Itens[i];
            }
        }
        throw new IllegalArgumentException("Este Item não existe na lista.");
    }

    public int VerificarItemPedido(int id){
        for(int i = 0; i <= contador; i++){
            if(this.Itens[i].getIdProduto() == id){
                return 1;
            }
        }

        throw new IllegalArgumentException("Este Item não existe na lista");
    }

    public void ModificarItemPedido(int id, int quantidade, int modificacao){

        VerificarItemPedido(id);

        for(int a = 0; a <= contador; a++){
            if(this.Itens[a].getIdProduto() == id
                    && this.Itens[a].getQuantidade() == quantidade){
                this.Itens[a].setQuantidade(modificacao);
            }
        }

    }

    public int getQuantItensPedidos(int id){

        VerificarItemPedido(id);

        int aux = 0;

        for(int i = 0; i <= contador; i++){
            if(this.Itens[i].getIdProduto() == id){
                aux++;
            }
        }

        return aux;
    }

    public int getQuantProdutosVendidos(int id){

        VerificarItemPedido(id);

        int aux = 0;
        for(int i = 0; i <= contador; i++){
            if(this.Itens[i].getIdProduto() == id){
                aux += this.Itens[i].getQuantidade();
            }
        }

        return aux;
    }

    public ItemPedido[] ListarItensPedidos(){
        ItemPedido[] aux = new ItemPedido[contador+1];

        for(int i = 0; i <= contador; i++){
            aux[i] = Itens[i];
        }

        return aux;
    }

}
