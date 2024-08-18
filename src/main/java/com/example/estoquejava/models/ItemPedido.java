package com.example.estoquejava.models;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    ///Propositalmente não tem nenhuma função de set pois vai gerar problema no repositório.

    public String getNomeProduto(){
        return this.produto.getNome();
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public double getValorProduto(){
        return this.produto.getPreco();
    }

    public double getValorItemPedido(){
        return (this.produto.getPreco() * this.quantidade);
    }

    public int getIdProduto(){
        return this.produto.getId();
    }

    @Override
    public String toString(){
        return ("Nome do produto: " + this.getNomeProduto() +
                "\nId do produto: " + this.getIdProduto() +
                "\nQuantidade de produtos: " + this.getQuantidade() +
                "\nValor do pedido: " + this.getValorItemPedido() +
                "\nValor do produto\n\n" + this.getValorProduto());
    }


}
