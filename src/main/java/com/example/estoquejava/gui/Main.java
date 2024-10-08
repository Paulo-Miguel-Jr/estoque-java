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
import com.example.estoquejava.models.*;
import com.example.estoquejava.models.enums.StatusPedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.LimiteItensAlcancadoException;
import com.example.estoquejava.models.exceptions.ItemPedNaoEncontException;

import java.time.LocalDate;
import java.util.Scanner;

import java.time.LocalDate;

public class Main {
    private static Fachada fachada = Fachada.getInstancia();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    buscarUsuario();
                    break;
                case 3:
                    removerUsuario();
                    break;
                case 4:
                    adicionarProduto();
                    break;
                case 5:
                    listarProdutos();
                    break;
                case 6:
                    adicionarPedido();
                    break;
                case 7:
                    adicionarItemPedido();
                    break;
                case 8:
                    listarPedidos();
                    break;
                case 9:
                    cancelarPedido();
                    break;
                case 10:
                    System.out.println("Saindo");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Buscar usuário por ID");
        System.out.println("3. Remover usuário por ID");
        System.out.println("4. Adicionar produto");
        System.out.println("5. Listar produtos");
        System.out.println("6. Adicionar pedido");
        System.out.println("7. Adicionar item ao pedido");
        System.out.println("8. Listar pedidos");
        System.out.println("9. Cancelar pedido");
        System.out.println("10. Sair");
    }

    private static void cadastrarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();
        System.out.println("Digite a senha do usuário:");
        String senha = scanner.nextLine();
        System.out.println("Digite o ID do usuário:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = new Usuario(nome, senha, id);
        fachada.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void buscarUsuario() {
        System.out.println("Digite o ID do usuário:");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Usuario usuario = fachada.buscarUsuarioPorId(id);
            System.out.println("Usuário encontrado: " + usuario);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removerUsuario() {
        System.out.println("Digite o ID do usuário a ser removido:");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            fachada.removerUsuario(id);
            System.out.println("Usuário removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void adicionarProduto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do produto:");
        String nome = scanner.nextLine();

        System.out.println("Digite o preço do produto:");
        double preco = scanner.nextDouble();

        System.out.println("Digite o ID do produto:");
        int id = scanner.nextInt();

        System.out.println("Digite a quantidade do produto:");
        double quantidade = scanner.nextDouble();

        System.out.println("Digite o estoque mínimo do produto:");
        int estoqueMinimo = scanner.nextInt();

        scanner.nextLine();

        //Criação do objeto Produto com todos os parâmetros
        Produto produto = new Produto(nome, id, preco, quantidade, estoqueMinimo);

        //adicionar o produto através da fachada
        Fachada fachada = Fachada.getInstancia();
        fachada.adicionarProduto(produto);

        System.out.println("Produto adicionado com sucesso!");
    }

    private static void listarProdutos() {
        Produto[] produtos = fachada.listarTodos();
        System.out.println("Lista de produtos:");
        for (Produto produto : produtos) {
            if (produto != null) {
                System.out.println(produto);
            }
        }
    }

    private static void adicionarPedido() {
        System.out.println("Digite o ID do pedido:");
        int idPedido = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite a data do pedido (YYYY-MM-DD):");
        LocalDate dataPedido = LocalDate.parse(scanner.nextLine());
        System.out.println("Digite o status do pedido (PENDENTE, PROCESSADO, CANCELADO):");
        StatusPedido status = StatusPedido.valueOf(scanner.nextLine().toUpperCase());

        Pedido pedido = new Pedido(idPedido, dataPedido, new ItemPedido[Pedido.LIMITE_ITENS], status);
        fachada.criarPedido(pedido);
        System.out.println("Pedido criado com sucesso!");
    }

    private static void adicionarItemPedido() {
        System.out.println("Digite o ID do pedido:");
        int idPedido = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite o ID do produto:");
        int idProduto = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite a quantidade do item:");
        double quantidade = scanner.nextDouble();
        scanner.nextLine();

        Produto produto = fachada.obterProdutoPorId(idProduto);
        ItemPedido itemPedido = new ItemPedido(produto);
        itemPedido.setQuantidade(quantidade);

        try {
            fachada.adicionarItemAoPedido(idPedido, itemPedido);
            System.out.println("Item adicionado ao pedido com sucesso!");
        } catch (LimiteItensAlcancadoException | ItemPedNaoEncontException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarPedidos() {
        Pedido[] pedidos = fachada.listarPedidos();
        System.out.println("Lista de pedidos:");
        for (Pedido pedido : pedidos) {
            if (pedido != null) {
                System.out.println(pedido);
            }
        }
    }

    private static void cancelarPedido() {
        System.out.println("Digite o ID do pedido a ser cancelado:");
        int idPedido = scanner.nextInt();
        scanner.nextLine();

        try {
            fachada.cancelarPedido(idPedido);
            System.out.println("Pedido cancelado com sucesso!");
        } catch (PedNaoEncontException e) {
            System.out.println(e.getMessage());
        }
    }



}


