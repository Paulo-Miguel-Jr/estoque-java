package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.Produto;

public interface ProdutoRepositorioInter {
    void adicionarProduto(Produto produto);
    Produto obterProdutoPorId(int id);
    Produto[] listarTodos();
    boolean removerProdutoPorId(int id);
    Produto[] buscarProdutosPorNome(String nome);

    void atualizarPrecos(double percentual);
    void notificarProdutosEmBaixa();
    void gerarRelatorioProdutosEmBaixa();
    void listarHistoricoAlteracoes();
    void salvarParaArquivo(String caminhoArquivo);
    void carregarDeArquivo(String caminhoArquivo);
    int getQuantidadeProdutos();
}
