package com.example.estoquejava.models;

public class ItemPedido {

    public static final int ITENS = 100;

    private Produto produto;
    private int quantidade;
    private int idItem;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    ///Propositalmente não tem nenhuma função de set pois vai gerar problema no repositório.

    public String getNomeProduto(){
        return this.produto.getNome();
    }

    public Produto getProduto() {
        return produto;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
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

    public void setQuantidade(int modificacao){
        this.quantidade = modificacao;
    }

    @Override
    public String toString() {
        return String.format(
                "Nome do produto: %s\n" +
                        "Id do produto: %d\n" +
                        "Quantidade de produtos: %d\n" +
                        "Valor do pedido: %.1f\n" +
                        "Valor do produto: %.1f",
                this.getNomeProduto(),
                this.getIdProduto(),
                this.getQuantidade(),
                this.getValorItemPedido(),
                this.getValorProduto()
        );
    }



}
