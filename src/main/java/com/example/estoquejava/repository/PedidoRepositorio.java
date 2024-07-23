package com.example.estoquejava.repository;

import com.example.estoquejava.models.PedidoFornecedor;

public class PedidoRepositorio {

    private PedidoFornecedor[] pedidos;
    private int proxIndLivre;

    public PedidoRepositorio(int numero) {
        this.pedidos = new PedidoFornecedor[numero];
        proxIndLivre = 0;
    }

    private int getProxIndLivre(int numero) {
        int n;
        boolean achou = false;
        int i = 0;
        while ((!achou) && (i < proxIndLivre)) {
            n = pedidos[i].getNumero();
            if (n == numero) {
                achou = true;
            } else {
                i += 1;
            }
        }
        return i;
    }

    public void adicionarPedido(PedidoFornecedor pedido) {
        pedidos[proxIndLivre] = pedido;
        proxIndLivre += 1;
        }

    public void removerPedido(int numero) {
        int i = getProxIndLivre(numero);
        if (i == proxIndLivre) {
            System.out.println("Pedido não encontrado");
        } else {
            pedidos[i] = pedidos[proxIndLivre - 1];
            proxIndLivre -= 1;
        }
    }

    public void atualizarPedido(PedidoFornecedor pedido)  {
        int i = getProxIndLivre(pedido.getNumero());
        if (i == proxIndLivre) {
            System.out.println("Pedido não encontrado.");
        } else {
            pedidos[i] = pedido;
        }
    }

    public void listarPedido() {
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null){
                System.out.println(pedidos[i]);
            }
        }
    }


    public PedidoFornecedor procurarPedido(int numero) {
        PedidoFornecedor resposta = null;
        int i = getProxIndLivre(numero);
        if (i == proxIndLivre) {
            System.out.println("Pedido não encontrado.");
        } else {
            resposta = pedidos[i];
        }
        return resposta;
    }

    

}