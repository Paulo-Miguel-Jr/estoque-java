package com.example.estoquejava.repository.interfaces;


import com.example.estoquejava.models.ItemPedido;
import com.example.estoquejava.models.Pedido;
import com.example.estoquejava.models.exceptions.PedNaoEncontException;
import com.example.estoquejava.models.exceptions.PedidoRepCheioException;

public interface IPedidoRepositorio {

<<<<<<< Updated upstream

    void adicionarPedido(Pedido pedido) throws PedidoRepCheioException;//trocar por cadastrar
=======
    //trocar numero por idPedido
    void adicionarPedido(Pedido pedido) throws PedidoRepCheioException;
>>>>>>> Stashed changes

    void removerPedido(int numero) throws PedNaoEncontException;

    void atualizarPedido(Pedido pedido) throws PedNaoEncontException;

    void listarPedidos();

    Pedido procurarPedido(int numero) throws PedNaoEncontException;

<<<<<<< Updated upstream
    void adicionarItemAoPedido (int numero, ItemPedido item) throws PedNaoEncontException;
=======
    void processarVenda(int numero) throws PedNaoEncontException;

    //void processarVenda(int numero) throws PedNaoEncontException;

    //adicionarItemAoPedido (int idPedido, ItemPedido item)
>>>>>>> Stashed changes

}
