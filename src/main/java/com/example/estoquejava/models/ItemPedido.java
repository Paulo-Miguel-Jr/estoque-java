package com.example.estoquejava.models;

import java.io.Serializable;

public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 65464L;

    public static final int ITENS = 100;

    private Produto produto;
    private double quantidade;
    private int idItem;

    public ItemPedido(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        this.produto = produto;
        this.quantidade = 0; // Inicializa quantidade com valor padrão
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getPrecoProduto() {
        if (produto != null) {
            return produto.getPreco();
        }
        return 0.0;
    }

    public String getNomeProduto() {
        if (produto != null) {
            return this.produto.getNome();
        }
        return "Nome não disponível";
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
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public double getValorProduto() {
        return getPrecoProduto();
    }

    public double getValorItemPedido() {
        return getPrecoProduto() * this.quantidade;
    }

    public int getIdProduto() {
        if (produto != null) {
            return this.produto.getId();
        }
        return -1; //valor padrão se o produto for nulo
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
