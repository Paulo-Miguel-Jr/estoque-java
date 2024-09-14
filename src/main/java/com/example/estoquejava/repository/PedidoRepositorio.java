package com.example.estoquejava.repository;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.interfaces.IPedidoRepositorio;

import java.io.*;

public class PedidoRepositorio implements IPedidoRepositorio, Serializable {
    private Pedido[] pedidos;
    private int proxIdLivre;
    private static PedidoRepositorio singletonPedRep;
    private ItemPedidoRepositorio itemPedidoRepositorio;

    private static final long serialVersionUID = 1234567890123456789L;

    public PedidoRepositorio() {
        this.pedidos = new Pedido[100];
        proxIdLivre = 0;
        this.itemPedidoRepositorio = ItemPedidoRepositorio.getInstance();
    }

    public static PedidoRepositorio getInstance(){
        if (singletonPedRep == null) {
          //  singletonPedRep = new PedidoRepositorio();
            singletonPedRep = lerDoArquivo();
        }
        return singletonPedRep;
    }


    private static PedidoRepositorio lerDoArquivo() {
        PedidoRepositorio instanciaLocal = null;

        File in = new File("pedidos.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (PedidoRepositorio) o;
        } catch (Exception e) {
            instanciaLocal = new PedidoRepositorio();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {/* Silent exception */
                }
            }
        }

        return instanciaLocal;
    }

    public void salvarArquivo() {
        if (singletonPedRep == null) {
            return;
        }
        File out = new File("pedidos.dat");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    /* Silent */}
            }
        }
    }


        public int getIdPedido(int idPedido) {
        for (int i = 0; i < proxIdLivre; i++) {
            if (pedidos[i].getIdPedido() == idPedido) {
                return i;
            }
        }
        return -1;
    }

    public int gerarNovoId() {
        return proxIdLivre + 1;
    }


    @Override
    public void adicionarPedido(Pedido pedido) throws PedidoRepCheioException {
        if (proxIdLivre < pedidos.length) {
            pedidos[proxIdLivre] = pedido;
            proxIdLivre++;
            salvarArquivo();
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
            salvarArquivo();
        }
    }


    @Override
    public void atualizarPedido(Pedido pedido) throws PedNaoEncontException {
        int indice = getIdPedido(pedido.getIdPedido());
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            pedidos[indice] = pedido;
            salvarArquivo();
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
    public Pedido procurarPedido(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }


    @Override
    public void adicionarItemAoPedido(int idPedido, ItemPedido novoItem) throws PedNaoEncontException, LimiteItensAlcancadoException {
        Pedido pedido = procurarPedido(idPedido);
        if (pedido == null) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        }

        if (pedido.getItensPedido().length >= Pedido.LIMITE_ITENS) {
            throw new LimiteItensAlcancadoException("Não é possível adicionar mais itens. Limite de itens alcançado.");
        }

        ItemPedido[] itensAtualizados = new ItemPedido[pedido.getItensPedido().length + 1];

        // Copia os itens antigos para o novo array
        System.arraycopy(pedido.getItensPedido(), 0, itensAtualizados, 0, pedido.getItensPedido().length);

        // Adiciona o novo item no final do array
        itensAtualizados[pedido.getItensPedido().length] = novoItem;

        pedido.setItensPedido(itensAtualizados);
        salvarArquivo();
    }

    public void processarVenda(int idPedido) throws PedNaoEncontException {
        int indice = getIdPedido(idPedido);
        if (indice == -1) {
            throw new PedNaoEncontException("Pedido não encontrado.");
        } else {
            Pedido pedido = pedidos[indice];
            if (pedido.getStatus() == StatusPedido.PENDENTE) {
                pedido.setStatus(StatusPedido.PROCESSADO);
                salvarArquivo();
            } else {
                System.out.println("O pedido já foi processado ou cancelado.");
            }
        }

    }

}


