package com.example.estoquejava.repository;

import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.interfaces.PedidoRepositorioInter;

public class PedidoRepositorio implements PedidoRepositorioInter {
    private Pedido[] pedidos;
    private int proxIdLivre;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public PedidoRepositorio(int capacidade) {
        this.pedidos = new Pedido[capacidade];
        proxIdLivre = 0;
        historicoAlteracoes = new String[capacidade * 2];
        contadorHistorico = 0;
    }

    private int getIdPedido(int numero) {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i].getIdPedido() == numero) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void adicionarPedido(Pedido pedido) throws PedidoRepCheioException {
        if (proxIdLivre < pedidos.length) {
            pedidos[proxIdLivre] = pedido;
            proxIdLivre++;
            adicionarHistorico("Pedido adicionado: " + pedido.getIdPedido());
        } else {
            throw new PedidoRepCheioException("Repositório cheio. Não é possível adicionar mais pedidos.");
        }
    }

    @Override
    public void removerPedido(int numero) throws PedNaoEncontException {
        int indice = getIdPedido(numero);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            adicionarHistorico("Pedido removido: " + pedidos[indice].getIdPedido());
            pedidos[indice] = pedidos[proxIdLivre - 1];
            pedidos[proxIdLivre - 1] = null;
            proxIdLivre--;
        }
    }

    @Override
    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException {
        int indice = getIdPedido(pedido.getIdPedido());
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
            adicionarHistorico("Pedido atualizado: " + pedido.getIdPedido());
        }
    }

    @Override
    public void listarPedidos() {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i] != null) {
                System.out.println(pedidos[i]);
            }
        }
    }

    @Override
    public Pedido procurarPedido(int numero) throws PedNaoEncontException {
        int indice = getIdPedido(numero);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            return pedidos[indice];
        }
    }

    @Override
    public void processarVenda(int numero) throws PedNaoEncontException {
        int indice = getIdPedido(numero);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            Pedido pedido = pedidos[indice];
            if (pedido.getStatus() == StatusPedido.PENDENTE) {
                pedido.setStatus(StatusPedido.PROCESSADO);
                adicionarHistorico("Venda processada para o pedido: " + pedido.getIdPedido());
            } else {
                System.out.println("O pedido já foi processado ou cancelado.");
            }
        }
    }

    @Override
    public void listarHistoricoAlteracoes() {
        System.out.println("Histórico de Alterações:");
        for (int i = 0; i < contadorHistorico; i++) {
            System.out.println(historicoAlteracoes[i]);
        }
    }

    private void adicionarHistorico(String alteracao) {
        if (contadorHistorico < historicoAlteracoes.length) {
            historicoAlteracoes[contadorHistorico] = alteracao;
            contadorHistorico++;
        } else {
            System.out.println("Histórico de alterações cheio.");
        }
    }
}
