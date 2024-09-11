package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.Produto;

public interface IProdutoRepositorio {
    void adicionarProduto(Produto produto);
    Produto obterProdutoPorId(int id);
    Produto[] listarTodos();
    boolean removerProdutoPorId(int id);
    Produto[] buscarProdutosPorNome(String nome);

    void atualizarPrecos(double percentual);
    void notificarProdutosEmBaixa();
    void gerarRelatorioProdutosEmBaixa();
    void listarHistoricoAlteracoes();
    int getQuantidadeProdutos();
}
