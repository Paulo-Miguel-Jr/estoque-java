package com.example.estoquejava.models;

import java.time.LocalDate;
import java.util.List;

public class PedidoFornecedor {
    private int numero;
    private LocalDate dataPedido;
    private List<ItemCompra> itensCompra;
    private double valorTotal;

    public PedidoFornecedor(int numero, LocalDate dataPedido, List<ItemCompra> itensCompra) {
        this.numero = numero;
        this.dataPedido = dataPedido;
        this.itensCompra = itensCompra;
        this.valorTotal = calcularValorTotal();
    }

    public PedidoFornecedor() {}

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<ItemCompra> itensCompra) {
        this.itensCompra = itensCompra;
        this.valorTotal = calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private double calcularValorTotal() {
        return itensCompra.stream().mapToDouble(ItemCompra::getValorTotal).sum();
    }

    @Override
    public String toString() {
        return "PedidoFornecedor{" +
                "numero=" + numero +
                ", dataPedido=" + dataPedido +
                ", itensCompra=" + itensCompra +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
