package com.example.estoquejava.models;

import com.example.estoquejava.repository.UsuarioRepositorio;

public class UsuarioCadastro {

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioCadastro(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = UsuarioRepositorio.getIn();
    }


    public void cadastrarUsuario(Usuario usuario){
        //colocar validações e regras de negócio
        if(usuario != null){
        usuarioRepositorio.adicionarUsuario(usuario);

        }else {

        }
    }
}
