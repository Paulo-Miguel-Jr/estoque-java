package com.example.estoquejava.models;

import java.time.LocalDate;
import java.util.List;

public class ItemCompra {
    private int codigo;
    private int quantidade;
    private double valorTotal;
    private LocalDate dataCompra;
    private Produto produto;

    public ItemCompra(int codigo, int quantidade, double valorTotal, LocalDate dataCompra, Produto produto) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.dataCompra = dataCompra;
        this.produto = produto;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
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

