package com.example.estoquejava.models;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.PedidoRepositorio;

import java.time.LocalDate;

public class TestePedido {

        public static void main(String[] args) {
            PedidoRepositorio pedidoRepositorio = PedidoRepositorio.getInstance();

            try {
                ItemPedido[] itens = new ItemPedido[0];

                Pedido pedido1 = new Pedido(1, LocalDate.now(), itens, StatusPedido.PENDENTE);
                Pedido pedido2 = new Pedido(2, LocalDate.now().minusDays(1), itens, StatusPedido.PENDENTE);

                pedidoRepositorio.adicionarPedido(pedido1);
                pedidoRepositorio.adicionarPedido(pedido2);

                System.out.println("Pedidos após adição:");
                pedidoRepositorio.listarPedidos();

                Produto produto1 = new Produto("Produto 1", 156, 50.00, 10, "quilo", 1);
                Produto produto2 = new Produto("Produto 2", 158, 30.00, 20, "unidade", 5);
                Produto produto3 = new Produto("Produto 3", 160, 25.00, 10, "quilo", 1);
                Produto produto4 = new Produto("Produto 4", 162, 10.00, 10, "quilo", 1);

                ItemPedido item1 = new ItemPedido(produto1);
                ItemPedido item2 = new ItemPedido(produto2);
                ItemPedido item3 = new ItemPedido(produto3);
                ItemPedido item4 = new ItemPedido(produto4);

                pedidoRepositorio.adicionarItemAoPedido(1, item1);
                pedidoRepositorio.adicionarItemAoPedido(1, item2);

                pedidoRepositorio.adicionarItemAoPedido(2, item3);
                pedidoRepositorio.adicionarItemAoPedido(2, item4);

                pedidoRepositorio.atualizarPedido(pedido1);
                pedidoRepositorio.atualizarPedido(pedido2);

                pedidoRepositorio.processarVenda(1);
                pedidoRepositorio.processarVenda(2);

                System.out.println("\nPedidos após processamento:");
                pedidoRepositorio.listarPedidos();

                pedidoRepositorio.removerPedido(1);

                System.out.println("\nPedidos após remoção:");
                pedidoRepositorio.listarPedidos();

                System.out.println("===========================");

                int[] idsExistentes = {1, 2};
                for (int id : idsExistentes) {
                    int index = pedidoRepositorio.getIdPedido(id);
                    System.out.println("Índice do pedido com ID " + id + ": " + index);
                }
                
                int invalidIndex = pedidoRepositorio.getIdPedido(150);
                System.out.println("Índice do pedido com ID 150: " + invalidIndex);

                System.out.println("===========================");

            } catch (PedidoRepCheioException e) {
                System.err.println("Erro ao adicionar pedido: " + e.getMessage());
            } catch (PedNaoEncontException e) {
                System.err.println("Erro ao processar pedido: " + e.getMessage());
            } catch (LimiteItensAlcancadoException e) {
                System.err.println("Erro ao adicionar item ao pedido: " + e.getMessage());
            }
        }
    }


