package com.example.estoquejava.models;

import java.io.Serializable;

public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 3L;

    public static final int ITENS = 100;

    private Produto produto;
    private double quantidade;
    private int idItem;

    public ItemPedido(Produto produto, double quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoProduto() {
        return produto.getPreco();
    }

    public String getNomeProduto() {
        return this.produto.getNome();
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public double getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public double getValorProduto() {
        return this.produto.getPreco();
    }

    public double getValorItemPedido() {
        return this.produto.getPreco() * this.quantidade;
    }

    public int getIdProduto() {
        return this.produto.getId();
    }

    @Override
    public String toString() {
        return String.format(
                "Nome do produto: %s\n" +
                        "Id do produto: %d\n" +
                        "Quantidade de produtos: %.2f\n" +
                        "Valor do pedido: %.2f\n" +
                        "Valor do produto: %.2f",
                this.getNomeProduto(),
                this.getIdProduto(),
                this.getQuantidade(),
                this.getValorItemPedido(),
                this.getValorProduto()
        );
    }
}
