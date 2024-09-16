package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.ItemPedNaoEncontException;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;

import java.io.Serializable;
import java.time.LocalDate;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 12345012345L;

    public static final int LIMITE_ITENS = 500;

    private int idPedido;
    private LocalDate dataPedido;
    private ItemPedido[] itensPedido;
    private int quantidadeItens;
    private double valorTotal;
    private StatusPedido status;

    public Pedido(int idPedido, LocalDate dataPedido, ItemPedido[] itensPedido, StatusPedido status) {
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.itensPedido = itensPedido;
        this.quantidadeItens = itensPedido.length;
        this.valorTotal = calcularValorTotal();
        this.status = status;
    }

    public Pedido() {
        this.itensPedido = new ItemPedido[LIMITE_ITENS];
        this.quantidadeItens = 0;
        this.status = StatusPedido.PENDENTE;
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


    public void setItensPedido(ItemPedido[] itensPedido) {
        this.itensPedido = itensPedido;
        this.quantidadeItens = itensPedido.length;
        this.valorTotal = calcularValorTotal();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public ItemPedido[] getItens() {
        return itensPedido;
    }

    public ItemPedido[] getItensPedido() {
        return itensPedido;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (int i = 0; i < quantidadeItens; i++) {
            if (itensPedido[i] != null) {
                total += itensPedido[i].getValorItemPedido();
            }
        }
        return total;
    }

    public void limparItens() {
        this.itensPedido = new ItemPedido[itensPedido.length];
        this.quantidadeItens = 0;
    }

    public void adicionarItemPedido(ItemPedido item) throws LimiteItensAlcancadoException {
        if (quantidadeItens < itensPedido.length) {
            itensPedido[quantidadeItens] = item;
            quantidadeItens++;
            this.valorTotal = calcularValorTotal();
        } else {
            throw new LimiteItensAlcancadoException("Não é possível adicionar mais itens. Limite de itens alcançado.");
        }
    }

    public void removerItemPedido(ItemPedido item) throws ItemPedNaoEncontException {
        boolean itemRemovido = false;

        for (int i = 0; i < quantidadeItens; i++) {
            if (itensPedido[i].equals(item)) {
                for (int j = i; j < quantidadeItens - 1; j++) {
                    itensPedido[j] = itensPedido[j + 1];
                }

                itensPedido[quantidadeItens - 1] = null;
                quantidadeItens--;
                itemRemovido = true;
                break;
            }
        }

        if (!itemRemovido) {
            throw new ItemPedNaoEncontException("Item não encontrado no pedido.");
        }

        this.valorTotal = calcularValorTotal();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Pedido{numero=%d, dataPedido=%s, status=%s\n", idPedido, dataPedido, status));
        sb.append("\nItens do Pedido:\n");

        if (quantidadeItens == 0) {
            sb.append("Nenhum item no pedido.\n");
        } else {
            for (int i = 0; i < quantidadeItens; i++) {
                sb.append(String.format("%d. —----------------------- \n", i + 1));
                sb.append(itensPedido[i].toString());
                sb.append("\n—----------------------- \n");
            }
        }

        sb.append(String.format("\nValor Total do Pedido: %.2f\n", valorTotal));
        sb.append('}');
        return sb.toString();
    }

}
