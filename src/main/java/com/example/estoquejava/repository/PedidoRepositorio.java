package com.example.estoquejava.repository;

import com.example.estoquejava.models.PedidoFornecedor;

public class PedidoRepositorio {
    private PedidoFornecedor[] pedidos;
    private int proxIdLivre;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public PedidoRepositorio(int capacidade) {
        this.pedidos = new PedidoFornecedor[capacidade];
        proxIdLivre = 0;
        historicoAlteracoes = new String[capacidade * 2]; //historico de alterações n deve exceder o dobro da capacidade
        contadorHistorico = 0;
    }

    private int getIdPedido(int numero) {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i].getNumero() == numero) {
                return i;
            }
        }
        return -1; //não encontrado
    }

    public void adicionarPedido(PedidoFornecedor pedido) {
        if (proxIdLivre < pedidos.length) {
            pedidos[proxIdLivre] = pedido;
            proxIdLivre++;
            adicionarHistorico("Pedido adicionado: " + pedido.getNumero());
        } else {
            System.out.println("Repositório cheio, não é possível adicionar mais pedidos.");
        }
    }

    public void removerPedido(int numero) {
        int indice = getIdPedido(numero);
        if (indice == -1) {
            System.out.println("Pedido não encontrado.");
        } else {
            adicionarHistorico("Pedido removido: " + pedidos[indice].getNumero());
            pedidos[indice] = pedidos[proxIdLivre - 1];
            pedidos[proxIdLivre - 1] = null;
            proxIdLivre--;
        }
    }

    public void atualizarPedido(PedidoFornecedor pedido) {
        int indice = getIdPedido(pedido.getNumero());
        if (indice == -1) {
            System.out.println("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
            adicionarHistorico("Pedido atualizado: " + pedido.getNumero());
        }
    }

    public void listarPedidos() {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i] != null) {
                System.out.println(pedidos[i]);
            }
        }
    }

    public PedidoFornecedor procurarPedido(int numero) {
        int indice = getIdPedido(numero);
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
