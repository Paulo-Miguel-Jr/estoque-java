package com.example.estoquejava.models;

import com.example.estoquejava.repository.PedidoRepositorio;

public class PedidoCadastro {
    private PedidoRepositorio pedidoRepositorio;

    public PedidoCadastro(PedidoRepositorio pedidoRepositorio){
        this.pedidoRepositorio = PedidoRepositorio.getInstance();
    }

}
