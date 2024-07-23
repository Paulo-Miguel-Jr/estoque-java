package com.example.estoquejava.repository;

import com.example.estoquejava.models.PedidoFornecedor;

public class PedidoRepositorio {

    private PedidoFornecedor[] pedidos;
    private int proxIndLivre;

    // Construtor da classe que inicializa o array 'pedidos' com o tamanho 'numero' e define 'proxIndLivre' como 0.
    public PedidoRepositorio(int numero) {
        this.pedidos = new PedidoFornecedor[numero];
        proxIndLivre = 0;
    }

    // Método que retorna o índice de um pedido com base no número do pedido.
    private int getProxIndLivre(int numero) {
        int n;
        boolean achou = false;
        int i = 0;
        // Loop que percorre os pedidos até encontrar um com o número especificado ou até alcançar o próximo índice livre.
        while ((!achou) && (i < proxIndLivre)) {
            n = pedidos[i].getNumero();
            if (n == numero) {
                achou = true; // Se o número do pedido for encontrado, 'achou' se torna true.
            } else {
                i += 1; // Se não for encontrado, o índice 'i' é incrementado.
            }
        }
        return i; // Retorna o índice onde o pedido foi encontrado ou o próximo índice livre.
    }


    public void adicionarPedido(PedidoFornecedor pedido) {
        pedidos[proxIndLivre] = pedido;  // Adiciona o pedido no próximo índice livre.
        proxIndLivre += 1;  // Incrementa o índice livre.
        }

    public void removerPedido(int numero) {
        int i = getProxIndLivre(numero);
        if (i == proxIndLivre) {  // Se o índice retornado for igual ao próximo índice livre, o pedido não foi encontrado.
            System.out.println("Pedido não encontrado");
        } else {
            pedidos[i] = pedidos[proxIndLivre - 1];  // Substitui o pedido a ser removido pelo último pedido no array.
            proxIndLivre -= 1; // Decrementa o índice livre.
        }
    }

    public void atualizarPedido(PedidoFornecedor pedido)  {
        int i = getProxIndLivre(pedido.getNumero());
        if (i == proxIndLivre) {  // Se o índice retornado for igual ao próximo índice livre, o pedido não foi encontrado.
            System.out.println("Pedido não encontrado.");
        } else {
            pedidos[i] = pedido; // Atualiza o pedido no índice encontrado.
        }
    }

    public void listarPedido() {
        for (int i = 0; i < pedidos.length; i++) {
            if (pedidos[i] != null){
                System.out.println(pedidos[i]);  // Imprime os pedidos que não são nulos.
            }
        }
    }


    public PedidoFornecedor procurarPedido(int numero) {
        PedidoFornecedor resposta = null; //Esta variável será usada para armazenar o pedido encontrado, se existir.
        int i = getProxIndLivre(numero); //retorna o índice do pedido no array pedidos se ele for encontrado, ou retorna o valor de proxIndLivre se não for encontrado.
        if (i == proxIndLivre) { // Se o índice retornado for igual ao próximo índice livre, o pedido não foi encontrado.
            System.out.println("Pedido não encontrado.");
        } else {
            resposta = pedidos[i]; // Atribui o pedido encontrado à variável resposta.
        }
        return resposta;
    }

    

}