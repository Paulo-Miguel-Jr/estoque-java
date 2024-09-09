package com.example.estoquejava.models;

import com.example.estoquejava.repository.UsuarioRepositorio;
import com.example.estoquejava.repository.PedidoRepositorio;
import com.example.estoquejava.repository.ItemPedidoRepositorio;

public class Fachada {

    private static Fachada instancia;

    private UsuarioCadastro usuarioCadastro;
    private PedidoCadastro pedidoCadastro;
    private ItemPedidoCadastro itempedidoCadastro;

    //faltava isso aqui
    private Fachada() {
        UsuarioRepositorio usuarioRepositorio = UsuarioRepositorio.getInstance();
        PedidoRepositorio pedidoRepositorio = PedidoRepositorio.getInstance();

        this.usuarioCadastro = new UsuarioCadastro(usuarioRepositorio);
        this.pedidoCadastro = new PedidoCadastro(pedidoRepositorio);
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;
    }

    //métodos usuario

    public void cadastrarUsuario(Usuario usuario) {
        usuarioCadastro.cadastrarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarioCadastro.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        return usuarioCadastro.buscarUsuarioPorNome(nome);
    }

    public void removerUsuario(int id) {
        usuarioCadastro.removerUsuario(id);
    }

    public void atualizarUsuario(Usuario usuario) {
        usuarioCadastro.atualizarUsuario(usuario);
    }

    public Usuario[] listarUsuarios() {
        return usuarioCadastro.listarUsuarios();
    }

    //métodos para a entidade Pedido

    public void criarPedido(Pedido pedido) {
        pedidoCadastro.criarPedido(pedido);
    }

    public void removerPedido(int numero) {
        pedidoCadastro.removerPedido(numero);
    }

    public void atualizarPedido(Pedido pedido) {
        pedidoCadastro.atualizarPedido(pedido);
    }

    public Pedido procurarPedido(int numero) {
        return pedidoCadastro.procurarPedido(numero);
    }

    public void processarVenda(int numero) {
        pedidoCadastro.processarVenda(numero);
    }

    public void listarPedidos() {
        pedidoCadastro.listarPedido();
    }

    public void adicionarItemAoPedido(int numero, ItemPedido item) {pedidoCadastro.adicionarItemAoPedido(numero, item );}

    //métodos ItemPedido

    public void criarItemPedido(ItemPedido item){itempedidoCadastro.inserirItemPedido(item);}

    public void deletarItemPedido(ItemPedido item){itempedidoCadastro.removerItemPedido(item);}

    public void atualizarItemPedido(ItemPedido item, int modificacao){itempedidoCadastro.alterarItemPedido(item, modificacao);}

    public ItemPedido procurarItemPedido(int id, int quantidade){return itempedidoCadastro.buscarItemPedido(id, quantidade);}

    public int getQuantItensVendidosPorId(int id){return itempedidoCadastro.getQuantItensPorId(id);}

    public int getQuantProdutosVendidosPorId(int id){return itempedidoCadastro.getQuantProdutosPorId(id);}

    public ItemPedido[] getListaItensPedidos(){return itempedidoCadastro.getListaItemPedido();}

}
