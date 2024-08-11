package com.example.estoquejava;

import com.example.estoquejava.models.*;
import com.example.estoquejava.models.enums.Categoria;
import com.example.estoquejava.models.enums.TipoProduto;
import com.example.estoquejava.repository.PedidoRepositorio;
import com.example.estoquejava.repository.ProdutoRepositorio;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //criar fornecedores

        Fornecedor fornecedor1 = new Fornecedor(
                "Fornecedor teste",
                1,
                "12.345.678/0001-90",
                "91241-1234",
                "12345-678",
                123, "Rua teste",
                "Apt 1",
                "Bairro teste",
                "Cidade teste",
                "Estado teste");

        Fornecedor fornecedor2 = new Fornecedor(
                "Fornecedor B",

                2,
                "23.456.789/0001-00",
                "99876-5432",
                "87654-321",

                456,
                "Rua B",
                "Apt 1",
                "Bairro 1",
                "Cidade 1"
                , "Estado 1");

        //criar produtos
        Produto produto1 = new Produto("Produto 1",
                101,
                10.0,
                100,
                "kg",
                10,
                Categoria.ALIMENTOS,
                TipoProduto.DETERGENTE,
                fornecedor1);

        Produto produto2 = new Produto("Produto 2",
                102,
                20.0,
                50,
                "litro",
                5,
                Categoria.BEBIDAS,
                TipoProduto.REFRIGERANTE,
                fornecedor2);

        Produto produto3 = new Produto("Produto 3",
                103,
                5.0,
                200,
                "unidade",
                20,
                Categoria.LIMPEZA,
                TipoProduto.CAMISA,
                fornecedor1);

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
        Pedido pedido1 = new Pedido(1, LocalDate.now(), itensCompra);
        Pedido pedido2 = new Pedido(2, LocalDate.now(), itensCompra);

        //criar repositórios
        ProdutoRepositorio produtoRepositorio = new ProdutoRepositorio(10);
        PedidoRepositorio pedidoRepositorio = new PedidoRepositorio(10);

        //adicionar produtos ao repositório
        produtoRepositorio.adicionarProduto(produto1);
        produtoRepositorio.adicionarProduto(produto2);
        produtoRepositorio.adicionarProduto(produto3);

        //adicionar pedidos ao repositório
        pedidoRepositorio.adicionarPedido(pedido1);
        pedidoRepositorio.adicionarPedido(pedido2);

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

        //filtrar produtos por categoria
        System.out.println("\nFiltrando produtos por categoria ALIMENTOS:");
        Produto[] produtosPorCategoria = produtoRepositorio.filtrarProdutosPorCategoria(Categoria.ALIMENTOS);
        for (Produto produto : produtosPorCategoria) {
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
        pedidoRepositorio.listarPedidos();

        //procurar pedido por número
        System.out.println("\nProcurando pedido número 1:");
        Pedido pedidoEncontrado = pedidoRepositorio.procurarPedido(1);
        if (pedidoEncontrado != null) {
            System.out.println(pedidoEncontrado);
        }

        //remover pedido por número
        System.out.println("\nRemovendo pedido número 1:");
        pedidoRepositorio.removerPedido(1);
        pedidoRepositorio.listarPedidos();

        //atualizar pedido
        pedido2.adicionarItemCompra(new ItemCompra(
                3,
                2,
                produto3.getPreco(),
                LocalDate.now(),
                produto3));
        pedidoRepositorio.atualizarPedido(pedido2);
        System.out.println("\nPedido número 2 após atualização:");
        pedidoRepositorio.listarPedidos();

        //listar histórico de alterações de pedidos
        System.out.println("\nHistórico de alterações de pedidos:");
        pedidoRepositorio.listarHistoricoAlteracoes();

        //teste de caso não encontrado
        System.out.println("\nTeste de caso não encontrado (Procurando pedido número 3):");
        Pedido pedidoNaoEncontrado = pedidoRepositorio.procurarPedido(3);
        if (pedidoNaoEncontrado == null) {
            System.out.println("Pedido número 3 não encontrado.");
        }

        //teste de repositório cheio
        System.out.println("\nTeste de repositório cheio:");
        for (int i = 0; i < 10; i++) {
            Pedido pedido = new Pedido(
                    i + 3,
                    LocalDate.now(),
                    new ItemCompra[]{itemCompra1}
            );
            pedidoRepositorio.adicionarPedido(pedido);
        }
        pedidoRepositorio.adicionarPedido(new Pedido(
                13,
                LocalDate.now(),
                new ItemCompra[]{itemCompra2}));

        //verificação final de todos os pedidos e histórico de alterações
        System.out.println("\nTodos os pedidos após testes:");
        pedidoRepositorio.listarPedidos();

    }
}
