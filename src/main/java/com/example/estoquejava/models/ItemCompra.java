package com.example.estoquejava.models;

import java.time.LocalDate;

public class
ItemCompra {
    private int codigo;
    private int quantidade;
    private double valorTotal;
    private LocalDate dataCompra;
    private Produto produto;

    public ItemCompra(int codigo, int quantidade, double valorTotal, LocalDate dataCompra, Produto produto) {
        this.setCodigo(codigo);
        this.setQuantidade(quantidade);
        this.setValorTotal(valorTotal);
        this.setDataCompra(dataCompra);
        this.setProduto(produto);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("Código deve ser positivo.");
        }
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("Valor total não pode ser negativo.");
        }
        this.valorTotal = valorTotal;
    }

    public double CalcularValorTotal() {
        return produto.getPreco()*quantidade;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        if (dataCompra == null || dataCompra.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de compra inválida.");
        }
        this.dataCompra = dataCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ItemCompra{" +
                "codigo=" + codigo +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                ", dataCompra=" + dataCompra +
                ", produto=" + produto +
                '}';
    }
}
