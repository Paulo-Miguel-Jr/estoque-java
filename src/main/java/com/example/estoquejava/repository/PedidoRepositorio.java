package com.example.estoquejava.repository;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.interfaces.IPedidoRepositorio;

public class PedidoRepositorio implements IPedidoRepositorio {
    private Pedido[] pedidos;
    private int proxIdLivre;
    private static PedidoRepositorio singletonPedRep;
    private ItemPedidoRepositorio itemPedidoRepositorio;
    
    public PedidoRepositorio() {
        this.pedidos = new Pedido[100];
        proxIdLivre = 0;
        this.itemPedidoRepositorio = ItemPedidoRepositorio.getInstance();
    }

    public static PedidoRepositorio getInstance(){
        if (singletonPedRep == null) {
            singletonPedRep = new PedidoRepositorio();
        }
        return singletonPedRep;
    }

    public int getIdPedido(int idPedido) {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i].getIdPedido() == idPedido) {
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
        } else {
            throw new PedidoRepCheioException("Repositório cheio. Não é possível adicionar mais pedidos.");
        }
    }

    @Override
    public void removerPedido(int idPedido) throws PedNaoEncontException {
        int indice = getIdPedido(idPedido);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            // Move todos os elementos após o índice para uma posição anterior
            for (int i = indice; i < proxIdLivre - 1; i++) {
                pedidos[i] = pedidos[i + 1];
            }
            // Limpa o último elemento, que foi movido para frente
            pedidos[proxIdLivre - 1] = null;
            proxIdLivre--;
        }
    }
   //else {
    //            pedidos[indice] = pedidos[proxIdLivre - 1];
    //            pedidos[proxIdLivre - 1] = null;
    //            proxIdLivre--;
    //        }


    @Override
    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException {
        int indice = getIdPedido(pedido.getIdPedido());
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
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
    public Pedido procurarPedido(int idPedido) throws PedNaoEncontException {
        int indice = getIdPedido(idPedido);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            return pedidos[indice];
        }
    }

    @Override
    public void adicionarItemAoPedido(int idPedido, ItemPedido novoItem) throws PedNaoEncontException, LimiteItensAlcancadoException {
        Pedido pedido = procurarPedido(idPedido);
        if (pedido == null) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        }

        // Verifica se o limite de itens foi alcançado
        if (pedido.getItensPedido().length >= Pedido.LIMITE_ITENS) {
            throw new LimiteItensAlcancadoException("Não é possível adicionar mais itens. Limite de itens alcançado.");
        }

        // Cria um novo array com tamanho incrementado
        ItemPedido[] itensAtualizados = new ItemPedido[pedido.getItensPedido().length + 1];

        // Copia os itens antigos para o novo array
        System.arraycopy(pedido.getItensPedido(), 0, itensAtualizados, 0, pedido.getItensPedido().length);

        // Adiciona o novo item no final do array
        itensAtualizados[pedido.getItensPedido().length] = novoItem;

        pedido.setItensPedido(itensAtualizados);
    }

    //    if (pedido != null) {
//            pedido.adicionarItemPedido(item);
    //      } else {
    //           throw new PedNaoEncontException("Pedido não encontrado.");
    //       }
//}


    public void processarVenda(int idPedido) throws PedNaoEncontException {
        int indice = getIdPedido(idPedido);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            Pedido pedido = pedidos[indice];
            if (pedido.getStatus() == StatusPedido.PENDENTE) {
                pedido.setStatus(StatusPedido.PROCESSADO);
            } else {
                System.out.println("O pedido já foi processado ou cancelado.");
            }
        }
    }


}
