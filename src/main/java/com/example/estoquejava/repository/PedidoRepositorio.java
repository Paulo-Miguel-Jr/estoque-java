package com.example.estoquejava.repository;

import com.example.estoquejava.models.PedidoFornecedor;

public class PedidoRepositorio {
    private PedidoFornecedor[] pedidos;
    private int proxIndLivre;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public PedidoRepositorio(int capacidade) {
        this.pedidos = new PedidoFornecedor[capacidade];
        proxIndLivre = 0;
        historicoAlteracoes = new String[capacidade * 2]; //historico de alterações n deve exceder o dobro da capacidade
        contadorHistorico = 0;
    }

    private int getIndicePedido(int numero) {
        for (int i = 0; i < proxIndLivre; i++) {
            if (pedidos[i].getNumero() == numero) {
                return i;
            }
        }
        return -1; //não encontrado
    }

    public void adicionarPedido(PedidoFornecedor pedido) {
        if (proxIndLivre < pedidos.length) {
            pedidos[proxIndLivre] = pedido;
            proxIndLivre++;
            adicionarHistorico("Pedido adicionado: " + pedido.getNumero());
        } else {
            System.out.println("Repositório cheio, não é possível adicionar mais pedidos.");
        }
    }

    public void removerPedido(int numero) {
        int indice = getIndicePedido(numero);
        if (indice == -1) {
            System.out.println("Pedido não encontrado.");
        } else {
            adicionarHistorico("Pedido removido: " + pedidos[indice].getNumero());
            pedidos[indice] = pedidos[proxIndLivre - 1];
            pedidos[proxIndLivre - 1] = null;
            proxIndLivre--;
        }
    }

    public void atualizarPedido(PedidoFornecedor pedido) {
        int indice = getIndicePedido(pedido.getNumero());
        if (indice == -1) {
            System.out.println("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
            adicionarHistorico("Pedido atualizado: " + pedido.getNumero());
        }
    }

    public void listarPedidos() {
        for (int i = 0; i < proxIndLivre; i++) {
            if (pedidos[i] != null) {
                System.out.println(pedidos[i]);
            }
        }
    }

    public PedidoFornecedor procurarPedido(int numero) {
        int indice = getIndicePedido(numero);
        if (indice == -1) {
            System.out.println("Pedido não encontrado.");
            return null;
        } else {
            return pedidos[indice];
        }
    }

    //histórico de alterações
    private void adicionarHistorico(String alteracao) {
        if (contadorHistorico < historicoAlteracoes.length) {
            historicoAlteracoes[contadorHistorico] = alteracao;
            contadorHistorico++;
        } else {
            System.out.println("Histórico de alterações cheio.");
        }
    }

    public void listarHistoricoAlteracoes() {
        System.out.println("Histórico de Alterações:");
        for (int i = 0; i < contadorHistorico; i++) {
            System.out.println(historicoAlteracoes[i]);
        }
    }

    // Serialização
    public void salvarParaArquivo(String caminhoArquivo) {
        //implementar método
    }

    public void carregarDeArquivo(String caminhoArquivo) {
        //implementar método
    }
}
