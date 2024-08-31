package com.example.estoquejava.models;

public class Fachada {

    private static Fachada instacia;

    private UsuarioCadastro usuarioCadastro;
    private PedidoCadastro pedidoCadastro;



    public static Fachada getInstacia() {
        if(instacia == null){
        instacia = new Fachada();
        }
        return instacia;

    }

    public void cadastrarUsuario(Usuario usuario){
        usuarioCadastro.cadastrarUsuario(usuario);
    }


    public void criarPedido(Pedido pedido) {
        pedidoCadastro.criarPedido(pedido); //perguntar a Paulo pq não está indo adicionarPedido
    }

    public void removerPedido(int numero)  {
        pedidoCadastro.removerPedido(numero);
    }

    public void atualizarPedido(Pedido pedido)  {
        pedidoCadastro.atualizarPedido(pedido);
    }

    public Pedido procurarPedido(int numero)  {
        pedidoCadastro.procurarPedido(numero);
    }

    public void processarVenda(int numero) {
        pedidoCadastro.processarVenda(numero);
    }

    public void listarPedidos() {
        pedidoCadastro.listarPedido();
    }

 //   public void adicionarItemAoPedido(int numero, ItemPedido item) {
  //      pedidoCadastro.adicionarItemPedido(item);
 //   }


}
