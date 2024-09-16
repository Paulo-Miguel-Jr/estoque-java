package com.example.estoquejava.models;

import com.example.estoquejava.models.exceptions.InvalidPedidoException;
import com.example.estoquejava.repository.ProdutoRepositorio;

public class ProdutoController {

    private ProdutoRepositorio produtoRepositorio;

    public ProdutoController() {this.produtoRepositorio = ProdutoRepositorio.getInstance();}

    public int criarPedido(Produto produto)  {
        produtoRepositorio.validarProduto(produto);
        int novoId = gerarIdProduto();
        produto.setId(novoId);
        produtoRepositorio.adicionarProduto(produto);
        return novoId;
    }

    private int gerarIdProduto() {
        return produtoRepositorio.gerarNovoId();
    }


    public void adicionarProduto(Produto produto) {
        produtoRepositorio.adicionarProduto(produto);
    }

    public Produto obterProdutoPorId(int id) {
        return produtoRepositorio.obterProdutoPorId(id);
    }

    public Produto[] listarTodos() {
        return produtoRepositorio.listarTodos();
    }

    public boolean removerProdutoPorId(int id) {
        Produto produto = obterProdutoPorId(id);
        if (produto == null) {
            throw new InvalidPedidoException("Produto com ID " + id + " não encontrado.");
        }
        return produtoRepositorio.removerProdutoPorId(id);
    }

    public Produto[] buscarProdutosPorNome(String nome) {
        return produtoRepositorio.buscarProdutosPorNome(nome);
    }

    public void atualizarPrecos(double percentual) {
        produtoRepositorio.atualizarPrecos(percentual);
    }

    public void notificarProdutosEmBaixa() {
        produtoRepositorio.notificarProdutosEmBaixa();
    }

    public void gerarRelatorioProdutosEmBaixa() {
        produtoRepositorio.gerarRelatorioProdutosEmBaixa();
    }

    public void listarHistoricoAlteracoes() {
        produtoRepositorio.listarHistoricoAlteracoes();
    }



}
