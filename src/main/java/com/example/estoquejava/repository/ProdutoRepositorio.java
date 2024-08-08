    package com.example.estoquejava.repository;

    import com.example.estoquejava.models.Produto;

    public class ProdutoRepositorio {

        private Produto[] produtos;
        private int contador;//contador para rastrear o número de produtos no repositório

        public ProdutoRepositorio(int capacidade) {
            produtos = new Produto[capacidade];
            contador = 0;
        }

        public void adicionarProduto(Produto produto) {
            if (contador < produtos.length) {
                produtos[contador] = produto;//adiciona o produto ao array e incrementa no contador
                contador++;
            } else {
                System.out.println("Repositório cheio, não é possível adicionar mais produtos.");
            }
        }

        public Produto obterProdutoPorId(int id) {//método para obter um produto pelo ID
            for (int i = 0; i < contador; i++) {
                if (produtos[i].getId() == id) {
                    return produtos[i];
                }
            }
            return null;
        }

        public Produto[] listarTodos() {//cria um novo array com o tamanho exato do número de produtos
            Produto[] lista = new Produto[contador];//copia os produtos do array principal para o novo array
            for (int i = 0; i < contador; i++) {
                lista[i] = produtos[i];
            }
            return lista; //retorna o novo array
        }

        public boolean removerProdutoPorId(int id) {
            for (int i = 0; i < contador; i++) {//se encontrar o produto com o ID especificado
                if (produtos[i].getId() == id) { //substitui o produto a ser removido pelo último produto do array
                    produtos[i] = produtos[contador - 1];//remove o último produto do array
                    produtos[contador - 1] = null;//tira lá do contador
                    contador--;
                    return true; //retorna true indicando que o produto foi removido com sucesso
                }
            }
            return false;
        }

    //    public boolean removerProdutoPorNome(String nome) {
    //        boolean produtoRemovido = false;
    //        for (int i = 0; i < contador; i++) {
    //            if (produtos[i].getNome().equalsIgnoreCase(nome)) {
    //                produtos[i] = produtos[contador - 1];
    //
    //                contador--;
    //                i--; // Ajusta o índice para verificar o novo produto na posição atual
    //                produtoRemovido = true;
    //            }
    //        }
    //        return produtoRemovido;

        public int getQuantidadeProdutos() {//método para obter a quantidade de produtos no repositório
            return contador;
        }

    }
