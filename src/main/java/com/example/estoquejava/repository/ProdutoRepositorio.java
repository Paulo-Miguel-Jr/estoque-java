package com.example.estoquejava.repository;

import com.example.estoquejava.models.enums.Categoria;
import com.example.estoquejava.models.Produto;

public class ProdutoRepositorio {
    private Produto[] produtos;
    private int contador;
    private String[] historicoAlteracoes;
    private int contadorHistorico;

    public ProdutoRepositorio(int capacidade) {
        produtos = new Produto[capacidade];
        contador = 0;
        historicoAlteracoes = new String[capacidade * 2]; //historico de alterações n deve exceder o dobro da capacidade
        contadorHistorico = 0;
    }

    //validação de Dados
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

    public void adicionarProduto(Produto produto) {
        if (contador < produtos.length) {
            if (validarProduto(produto)) {
                produtos[contador] = produto;
                contador++;
                adicionarHistorico("Produto adicionado: " + produto.getNome());
            } else {
                System.out.println("Produto inválido. Não foi possível adicionar.");
            }
        } else {
            System.out.println("Repositório cheio, não é possível adicionar mais produtos.");
        }
    }

    public Produto obterProdutoPorId(int id) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getId() == id) {
                return produtos[i];
            }
        }
        return null;
    }

    public Produto[] listarTodos() {
        Produto[] lista = new Produto[contador];
        for (int i = 0; i < contador; i++) {
            lista[i] = produtos[i];
        }
        return lista;
    }

    public boolean removerProdutoPorId(int id) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getId() == id) {
                adicionarHistorico("Produto removido: " + produtos[i].getNome());
                produtos[i] = produtos[contador - 1];
                produtos[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

    //relatórios
    public void gerarRelatorioProdutosEmBaixa() {
        System.out.println("Relatório de Produtos em Baixa Quantidade:");
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()) {
                System.out.println(produtos[i]);
            }
        }
    }

    //busca e filtros
    public Produto[] buscarProdutosPorNome(String nome) {
        Produto[] resultado = new Produto[contador];
        int resultadoContador = 0;
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado[resultadoContador] = produtos[i];
                resultadoContador++;
            }
        }
        Produto[] resultadoFinal = new Produto[resultadoContador];
        System.arraycopy(resultado, 0, resultadoFinal, 0, resultadoContador);
        return resultadoFinal;
    }

    /*
    public Produto[] filtrarProdutosPorCategoria(Categoria categoria) {
        Produto[] resultado = new Produto[contador];
        int resultadoContador = 0;
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getCategoria() == categoria) {
                resultado[resultadoContador] = produtos[i];
                resultadoContador++;
            }
        }
        Produto[] resultadoFinal = new Produto[resultadoContador];
        System.arraycopy(resultado, 0, resultadoFinal, 0, resultadoContador);
        return resultadoFinal;
    }
    */

    //atualização em massa
    public void atualizarPrecos(double percentual) {
        for (int i = 0; i < contador; i++) {
            double novoPreco = produtos[i].getPreco() * (1 + percentual / 100);
            produtos[i].setPreco(novoPreco);
            adicionarHistorico("Preço atualizado para o produto: " + produtos[i].getNome() + " para " + novoPreco);
        }
    }

    //notificações
    public void notificarProdutosEmBaixa() {
        System.out.println("Notificação de Produtos em Baixa Quantidade:");
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getQuantidade() < produtos[i].getEstoqueMinimo()) {
                System.out.println("Produto: " + produtos[i].getNome() + " está em baixa quantidade.");
            }
        }
    }

    //histórico de alterações
    private void adicionarHistorico(String alteracao) {
        if (contadorHistorico < historicoAlteracoes.length) {
            historicoAlteracoes[contadorHistorico] = alteracao;
            contadorHistorico++;
        } else {
            System.out.println("Histórico de alterações cheio.");
        }
    }

    public void listarHistoricoAlteracoes() {
        System.out.println("Histórico de Alterações:");
        for (int i = 0; i < contadorHistorico; i++) {
            System.out.println(historicoAlteracoes[i]);
        }
    }

    // Serialização
    public void salvarParaArquivo(String caminhoArquivo) {
        //implementar método
    }

    public void carregarDeArquivo(String caminhoArquivo) {
        //implementar método
    }

    public int getQuantidadeProdutos() {
        return contador;
    }
}
