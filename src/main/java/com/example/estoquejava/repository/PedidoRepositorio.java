package com.example.estoquejava.repository;

import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;

public class PedidoRepositorio {
    private Pedido[] pedidos;
    private int proxIdLivre;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public PedidoRepositorio(int capacidade) {
        this.pedidos = new Pedido[capacidade];
        proxIdLivre = 0;
        historicoAlteracoes = new String[capacidade * 2]; //historico de alterações não deve exceder o dobro da capacidade
        contadorHistorico = 0;
    }

    private int getIdPedido(int numero) {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i].getIdPedido() == numero) {
                return i;
            }
        }
        return -1; // pedido não encontrado
    }

    public void adicionarPedido(Pedido pedido) throws PedidoRepCheioException {
        if (proxIdLivre < pedidos.length) {
            pedidos[proxIdLivre] = pedido;
            proxIdLivre++;
            adicionarHistorico("Pedido adicionado: " + pedido.getIdPedido());
        } else {
            throw new PedidoRepCheioException("Repositorio cheio. Não é possível adicionar mais pedidos.");
        }
    }

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

    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException {
        int indice = getIdPedido(pedido.getIdPedido());
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
            adicionarHistorico("Pedido atualizado: " + pedido.getIdPedido());
        }
    }

    public void listarPedidos() {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i] != null) {
                System.out.println(pedidos[i]);
            }
        }
    }

    public Pedido procurarPedido(int numero) throws PedNaoEncontException {
        int indice = getIdPedido(numero);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
            // return null; - nunca executado
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

    //avaliar se é permitido arraylist no repositorio (tam é acessado por size)

    // Serialização
   // public void salvarParaArquivo(String caminhoArquivo) {
        //implementar método
   // }

  //  public void carregarDeArquivo(String caminhoArquivo) {
        //implementar método
   // }
}
