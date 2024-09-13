//package com.example.estoquejava.gui;
//
//import com.example.estoquejava.models.Fachada;
//import com.example.estoquejava.models.Usuario;
//import com.example.estoquejava.models.enums.TipoUsuario;
//
//public class Main {
//
//    public static void main(String[] args){
//
//        Usuario usuario = new Usuario("Wendell", "123", 1, TipoUsuario.USUARIO);
//        Fachada.getInstacia().cadastrarUsuario(usuario);// chamar na tela esse método
//    }
//}
package com.example.estoquejava.gui;

import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.Produto;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;
import com.example.estoquejava.repository.PedidoRepositorio;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Cria uma instância do repositório de pedidos
        PedidoRepositorio pedidoRepositorio = PedidoRepositorio.getInstance();

        try {
            // Cria um array de itens de pedido (vazio inicialmente)
            ItemPedido[] itens = new ItemPedido[0];

            // Cria alguns pedidos com datas e status
            Pedido pedido1 = new Pedido(1, LocalDate.now(), itens, StatusPedido.PENDENTE);
            Pedido pedido2 = new Pedido(2, LocalDate.now().minusDays(1), itens, StatusPedido.PENDENTE);

            // Adiciona os pedidos ao repositório
            pedidoRepositorio.adicionarPedido(pedido1);
            pedidoRepositorio.adicionarPedido(pedido2);


            // Lista os pedidos atuais
            System.out.println("Pedidos após adição:");
            pedidoRepositorio.listarPedidos();

            // Criação de produtos
            Produto produto1 = new Produto("Produto 1", 156, 50.00, 10, "quilo", 1);
            Produto produto2 = new Produto("Produto 2", 158, 30.00, 20, "unidade", 5);

            Produto produto3 = new Produto("Produto 3", 160, 25.00, 10, "quilo", 1);
            Produto produto4 = new Produto("Produto 4", 162, 10.00, 10, "quilo", 1);

            // Cria itens de pedido
            ItemPedido item1 = new ItemPedido(produto1);
            ItemPedido item2 = new ItemPedido(produto2);

            ItemPedido item3 = new ItemPedido(produto3);
            ItemPedido item4 = new ItemPedido(produto4);


            // Adiciona itens ao pedido1
            pedidoRepositorio.adicionarItemAoPedido(1, item1);
            pedidoRepositorio.adicionarItemAoPedido(1, item2);

            // Adiciona itens ao pedido2
            pedidoRepositorio.adicionarItemAoPedido(2, item3);
            pedidoRepositorio.adicionarItemAoPedido(2, item4);

            // Atualiza o pedido após adicionar os itens
            pedidoRepositorio.atualizarPedido(pedido1);
            pedidoRepositorio.atualizarPedido(pedido2);


            // Processa a venda do pedido1
            pedidoRepositorio.processarVenda(1);
            pedidoRepositorio.processarVenda(2);

            // Lista os pedidos após o processamento
            System.out.println("\nPedidos após processamento:");
            pedidoRepositorio.listarPedidos();

            // Remove o pedido1
           pedidoRepositorio.removerPedido(1);

            // Lista os pedidos após a remoção
            System.out.println("\nPedidos após remoção:");
            pedidoRepositorio.listarPedidos();


            System.out.println("===========================");

            //Testa o método getIdPedido para um pedido que existe
            int[] idsExistentes = {1, 2};
            for (int id : idsExistentes) {
                int index = pedidoRepositorio.getIdPedido(id);
                System.out.println("Índice do pedido com ID " + id + ": " + index);
            }


            // Testa o método getIdPedido para um pedido que não existe
            int invalidIndex = pedidoRepositorio.getIdPedido(150);
            System.out.println("Índice do pedido com ID 999: " + invalidIndex);

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


