package com.example.estoquejava.models;

import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;


import java.time.LocalDate;

public class Pedido {
    private int idPedido;
    private LocalDate dataPedido;
    private ItemCompra[] itensCompra;
    private int quantidadeItens;
    private double valorTotal;

    public Pedido(int idPedido, LocalDate dataPedido, ItemCompra[] itensCompra) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.itensCompra = itensCompra;
        this.quantidadeItens = itensCompra.length;
        this.valorTotal = calcularValorTotal();
    }

    public Pedido() {
        this.itensCompra = new ItemCompra[10];
        this.quantidadeItens = 0;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public ItemCompra[] getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItemCompra[] itensCompra) {
        this.itensCompra = itensCompra;
        this.quantidadeItens = itensCompra.length;
        this.valorTotal = calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    private double calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < quantidadeItens; i++) {
            if (itensCompra[i] != null) {
                total += itensCompra[i].getValorTotal();
            }
        }
        return total;
    }

    public void adicionarItemCompra(ItemCompra item) throws LimiteItensAlcancadoException {
        if (quantidadeItens < itensCompra.length) {
            itensCompra[quantidadeItens] = item;
            quantidadeItens++;
            this.valorTotal = calcularValorTotal();
        } else {
           throw new LimiteItensAlcancadoException("Não é possível adicionar mais itens. Limite de itens alcançado.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PedidoFornecedor{");
        sb.append("numero=").append(idPedido);
        sb.append(", dataPedido=").append(dataPedido);
        sb.append(", itensCompra=[");
        for (int i = 0; i < quantidadeItens; i++) {
            sb.append(itensCompra[i].toString());
            if (i < quantidadeItens - 1) sb.append(", ");
        }
        sb.append("]");
        sb.append(", valorTotal=").append(valorTotal);
        sb.append('}');
        return sb.toString();
    }
}
