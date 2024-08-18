package com.example.estoquejava.repository;

import com.example.estoquejava.models.Produto;
import com.example.estoquejava.repository.interfaces.ProdutoRepositorioInter;

public class ProdutoRepositorio implements ProdutoRepositorioInter {
    private Produto[] produtos;
    private int contador;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public ProdutoRepositorio(int capacidade) {
        produtos = new Produto[capacidade];
        contador = 0;
        historicoAlteracoes = new String[capacidade * 2];
        contadorHistorico = 0;
    }

    //validação de dados
    private boolean validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            System.out.println("Nome do produto é obrigatório.");
            return false;
        }
        if (produto.getPreco() < 0) {
            System.out.println("Preço do produto não pode ser negativo.");
            return false;
        }
        return true;
    }

    @Override
    public void adicionarProduto(Produto produto) {
        if (contador < produtos.length) {
            if (validarProduto(produto)) {
                produtos[contador++] = produto;
                adicionarHistorico("Produto adicionado: " + produto.getNome());
            } else {
                System.out.println("Produto inválido. Não foi possível adicionar.");
            }
        } else {
            System.out.println("Repositório cheio, não é possível adicionar mais produtos.");
        }
    }

    @Override
    public Produto obterProdutoPorId(int id) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getId() == id) {
                return produtos[i];
            }
        }
        return null;
    }

    @Override
    public Produto[] listarTodos() {
        Produto[] lista = new Produto[contador];
        System.arraycopy(produtos, 0, lista, 0, contador);
        return lista;
    }

    @Override
    public boolean removerProdutoPorId(int id) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getId() == id) {
                adicionarHistorico("Produto removido: " + produtos[i].getNome());
                produtos[i] = produtos[--contador];
                produtos[contador] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public Produto[] buscarProdutosPorNome(String nome) {
        Produto[] resultado = new Produto[contador];
        int resultadoContador = 0;
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado[resultadoContador++] = produtos[i];
            }
        }
        Produto[] resultadoFinal = new Produto[resultadoContador];
        System.arraycopy(resultado, 0, resultadoFinal, 0, resultadoContador);
        return resultadoFinal;
    }

    @Override
    public void atualizarPrecos(double percentual) {
        for (int i = 0; i < contador; i++) {
            double novoPreco = produtos[i].getPreco() * (1 + percentual / 100);
            produtos[i].setPreco(novoPreco);
            adicionarHistorico("Preço atualizado: " + produtos[i].getNome() + " para " + novoPreco);
        }
    }

    @Override
    public void notificarProdutosEmBaixa() {
        System.out.println("Notificação de Produtos em Baixa Quantidade:");
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()) {
                System.out.println("Produto: " + produtos[i].getNome() + " está em baixa quantidade.");
            }
        }
    }

    @Override
    public void gerarRelatorioProdutosEmBaixa() {
        System.out.println("Relatório de Produtos em Baixa Quantidade:");
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()) {
                System.out.println(produtos[i]);
            }
        }
    }

    @Override
    public void listarHistoricoAlteracoes() {
        System.out.println("Histórico de Alterações:");
        for (int i = 0; i < contadorHistorico; i++) {
            System.out.println(historicoAlteracoes[i]);
        }
    }

    private void adicionarHistorico(String alteracao) {
        if (contadorHistorico < historicoAlteracoes.length) {
            historicoAlteracoes[contadorHistorico++] = alteracao;
        } else {
            System.out.println("Histórico de alterações cheio.");
        }
    }

    @Override
    public void salvarParaArquivo(String caminhoArquivo) {
        //implementação do método de serialização
    }

    @Override
    public void carregarDeArquivo(String caminhoArquivo) {
        //implementação do método de desserialização
    }

    @Override
    public int getQuantidadeProdutos() {
        return contador;
    }
}
