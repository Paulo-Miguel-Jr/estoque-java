package com.example.estoquejava.models;
import java.time.LocalDate;
import java.util.List;

public class PedidoFornecedor {
    private int numero;
    private LocalDate dataPedido;
    private List<ItemCompra> itemCompra;
    private double valorTotal;

    public PedidoFornecedor(int numero, LocalDate dataPedido, List<ItemCompra> itemCompra, double valorTotal) {
        this.numero = numero;
        this.dataPedido = dataPedido;
        this.itemCompra = itemCompra;
        this.valorTotal = valorTotal;
    }

    public PedidoFornecedor() {

    }

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

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "PedidoFornecedor{" +
                "numero=" + numero +
                ", dataPedido=" + dataPedido +
                ", List<ItemCompra>=" + itemCompra +
                '}';
    }
}
