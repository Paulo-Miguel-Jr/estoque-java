package com.example.estoquejava;

import com.example.estoquejava.models.*;
import com.example.estoquejava.models.enums.Categoria;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.repository.PedidoRepositorioImpl;
import com.example.estoquejava.repository.ProdutoRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //criar produtos
        Produto produto1 = new Produto("Produto 1",
                101,
                10.0,
                100,
                "kg",
                10);

        Produto produto2 = new Produto("Produto 2",
                102,
                20.0,
                50,
                "litro",
                5);

        Produto produto3 = new Produto("Produto 3",
                103,
                5.0,
                200,
                "unidade",
                20);

        //criar itens de compra
        ItemCompra itemCompra1 = new ItemCompra(
                1,
                10,
                produto1.getPreco(),
                LocalDate.now(),
                produto1);

        ItemCompra itemCompra2 = new ItemCompra(
                2,
                5,
                produto2.getPreco(),
                LocalDate.now(),
                produto2);

        //criar pedidos
        ItemCompra[] itensCompra = {itemCompra1, itemCompra2};
        Pedido pedido1 = new Pedido(1, LocalDate.now(), itensCompra, StatusPedido.PENDENTE);
        Pedido pedido2 = new Pedido(2, LocalDate.now(), itensCompra, StatusPedido.PENDENTE);

        //criar repositórios
        ProdutoRepositoryImpl produtoRepositorio = new ProdutoRepositoryImpl(10);
        PedidoRepositorioImpl pedidoRepositorioImpl = new PedidoRepositorioImpl(10);

        //adicionar produtos ao repositório
        produtoRepositorio.adicionarProduto(produto1);
        produtoRepositorio.adicionarProduto(produto2);
        produtoRepositorio.adicionarProduto(produto3);

        //adicionar pedidos ao repositório
        pedidoRepositorioImpl.adicionarPedido(pedido1);
        pedidoRepositorioImpl.adicionarPedido(pedido2);

        //listar todos os produtos
        System.out.println("Todos os produtos:");
        Produto[] todosProdutos = produtoRepositorio.listarTodos();
        for (Produto produto : todosProdutos) {
            System.out.println(produto);
        }

        //buscar produtos por nome
        System.out.println("\nBuscando produtos por nome 'Produto 1':");
        Produto[] produtosPorNome = produtoRepositorio.buscarProdutosPorNome("Produto 1");
        for (Produto produto : produtosPorNome) {
            System.out.println(produto);
        }


        //atualizar preços
        produtoRepositorio.atualizarPrecos(10.0);
        System.out.println("\nProdutos após atualizar preços em 10%:");
        todosProdutos = produtoRepositorio.listarTodos();
        for (Produto produto : todosProdutos) {
            System.out.println(produto);
        }

        //notificar produtos em baixa
        System.out.println("\nNotificações de produtos em baixa:");
        produtoRepositorio.notificarProdutosEmBaixa();

        //listar histórico de alterações
        System.out.println("\nHistórico de alterações:");
        produtoRepositorio.listarHistoricoAlteracoes();

        //listar todos os pedidos
        System.out.println("\nTodos os pedidos:");
        pedidoRepositorioImpl.listarPedidos();

        //procurar pedido por número
        System.out.println("\nProcurando pedido número 1:");
        Pedido pedidoEncontrado = pedidoRepositorioImpl.procurarPedido(1);
        if (pedidoEncontrado != null) {
            System.out.println(pedidoEncontrado);
        }

        //remover pedido por número
        System.out.println("\nRemovendo pedido número 1:");
        pedidoRepositorioImpl.removerPedido(1);
        pedidoRepositorioImpl.listarPedidos();

        //atualizar pedido
        pedido2.adicionarItemCompra(new ItemCompra(
                3,
                2,
                produto3.getPreco(),
                LocalDate.now(),
                produto3));
        pedidoRepositorioImpl.atualizarPedido(pedido2);
        System.out.println("\nPedido número 2 após atualização:");
        pedidoRepositorioImpl.listarPedidos();

        //listar histórico de alterações de pedidos
        System.out.println("\nHistórico de alterações de pedidos:");
        pedidoRepositorioImpl.listarHistoricoAlteracoes();

        //teste de caso não encontrado
        System.out.println("\nTeste de caso não encontrado (Procurando pedido número 3):");
        Pedido pedidoNaoEncontrado = pedidoRepositorioImpl.procurarPedido(3);
        if (pedidoNaoEncontrado == null) {
            System.out.println("Pedido número 3 não encontrado.");
        }

        //teste de repositório cheio
        System.out.println("\nTeste de repositório cheio:");
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido(
                    i + 3,
                    LocalDate.now(),
                    new ItemCompra[]{itemCompra1},
                    StatusPedido.PENDENTE
            );
            pedidoRepositorioImpl.adicionarPedido(pedido);
        }
        pedidoRepositorioImpl.adicionarPedido(new Pedido(
                13,
                LocalDate.now(),
                new ItemCompra[]{itemCompra2},
                StatusPedido.PENDENTE));

        //verificação final de todos os pedidos e histórico de alterações
        System.out.println("\nTodos os pedidos após testes:");
        pedidoRepositorioImpl.listarPedidos();

    }
}
