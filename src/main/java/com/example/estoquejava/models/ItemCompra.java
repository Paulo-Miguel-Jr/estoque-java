package com.example.estoquejava.models;

import java.time.LocalDate;
import java.util.List;

public class ItemCompra {
    private int codigo;
    private int quantidade;
    private double valor;
    private LocalDate dataCompra;
    private Produto produto;

    public ItemCompra(int codigo, int quantidade, double valor, LocalDate dataCompra, LocalDate dataEntrega, Produto produto) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataCompra = dataCompra;
        this.dataEntrega = dataEntrega;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
                ", valor=" + valor +
                ", dataCompra=" + dataCompra +
                ", produto=" + produto +
                '}';
    }

    
}

