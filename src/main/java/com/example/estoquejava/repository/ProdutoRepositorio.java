package com.example.estoquejava.repository;

import com.example.estoquejava.models.Produto;
import com.example.estoquejava.repository.interfaces.IProdutoRepositorio;

import java.io.*;

public class ProdutoRepositorio implements IProdutoRepositorio, Serializable {

    private static final long serialVersionUID = 1L;
    private Produto[] produtos;
    private int contador;
    private String[] historicoAlteracoes;
    private int contadorHistorico;
    private static ProdutoRepositorio instance;


    public ProdutoRepositorio(int capacidade) {
        produtos = new Produto[capacidade];
        contador = 0;
        historicoAlteracoes = new String[capacidade * 2];
        contadorHistorico = 0;
    }

    public static ProdutoRepositorio getInstance(){
        if (instance == null) {
            instance = lerDoArquivo();
        }
        return instance;
    }

    //validação de dados
    public boolean validarProduto(Produto produto) {
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

    //Checar se o produto já existe no repositório (por nome ou ID)
    private boolean produtoDuplicado(Produto produto) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getNome().equalsIgnoreCase(produto.getNome()) || produtos[i].getId() == produto.getId()) {
                return true; // Produto duplicado
            }
        }
        return false;
    }

    @Override
    public void adicionarProduto(Produto produto) {
        if (contador >= produtos.length) {
            System.out.println("Repositório cheio, não é possível adicionar mais produtos.");
            return;
        }

        if (validarProduto(produto)) {
            if (!produtoDuplicado(produto)) {
                produtos[contador++] = produto;
                adicionarHistorico("Produto adicionado: " + produto.getNome());
            } else {
                System.out.println("Produto duplicado. Não foi possível adicionar.");
            }
        } else {
            System.out.println("Produto inválido. Não foi possível adicionar.");
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
    public String gerarRelatorioProdutosEmBaixa() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Produtos em Baixa Quantidade:\n");

        for (Produto produto : listarTodos()) {
            if (produto.getQuantidade() < produto.getEstoqueMinimo()) {
                relatorio.append("ID: ").append(produto.getId())
                        .append(", Nome: ").append(produto.getNome())
                        .append(", Quantidade: ").append(produto.getQuantidade())
                        .append(", Estoque Mínimo: ").append(produto.getEstoqueMinimo())
                        .append("\n");
            }
        }

        // Verifica se nenhum produto está em baixa quantidade
        if (relatorio.length() == "Relatório de Produtos em Baixa Quantidade:\n".length()) {
            return "Todos os produtos estão com estoque adequado.";
        }

        return relatorio.toString();
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

    //atualizar um produto no repositório
    public boolean atualizarProduto(Produto produtoAtualizado) {
        for (int i = 0; i < contador; i++) {
            if (produtos[i].getId() == produtoAtualizado.getId()) {
                produtos[i] = produtoAtualizado; //Atualiza o produto no array
                salvarArquivo();
                return true; //retorna vedadeiro se a atualização acontecer
            }
        }
        return false; //Retorna falso se o produto não for encontrado
    }

    private static ProdutoRepositorio lerDoArquivo() {
        ProdutoRepositorio instanciaLocal = null;

        File in = new File("produtos.dat");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            instanciaLocal = (ProdutoRepositorio) o;
        } catch (Exception e) {
            instanciaLocal = new ProdutoRepositorio(100);
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
        if (instance == null) {
            return;
        }
        File out = new File("produtos.dat");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(out);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
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

    public int gerarNovoId() {
        return contador + 1;
    }

    @Override
    public int getQuantidadeProdutos() {
        return contador;
    }
}
