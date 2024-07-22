package com.example.estoquejava.models;
import java.time.LocalDate;

public class PedidoFornecedor {
    private int numero;
    private LocalDate dataPedido;
    private Lista<ItemCompra> itemCompra;

    public PedidoFornecedor(int numero, LocalDate dataPedido, Lista<ItemCompra> itemCompra) {
        this.numero = numero;
        this.dataPedido = dataPedido;
        this.itemCompra = itemCompra;
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


      public List<ItemCompra> getitemCompra() {
        return itemCompra;
    }

    public void setProdutos(List<ItemCoompra> itemCompra) {
        this.itemCompra = itemCompra;
    }


    @Override
    public String toString() {
        return "PedidoFornecedor{" +
                "numero=" + numero +
                ", dataPedido=" + dataPedido +
                ", Lista<ItemCompra>=" + itemCompra +
                '}';
    }
}
