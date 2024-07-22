package com.example.estoquejava.models;

import java.time.LocalDate;
import java.util.List;

public class ItemCompra {
    private int codigo;
    private int quantidade;
    private double valor;
    private LocalDate dataCompra;
    private Produto produto;

    public ItemCompra(int codigo, int quantidade, double valor, LocalDate dataCompra, LocalDate dataEntrega, List<Produto> produtos) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataCompra = dataCompra;
        this.dataEntrega = dataEntrega;
        this.produtos = produtos;
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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "ItemCompra{" +
                "codigo=" + codigo +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", dataCompra=" + dataCompra +
                ", dataEntrega=" + dataEntrega +
                ", produtos=" + produtos +
                '}';
    }

    
}

