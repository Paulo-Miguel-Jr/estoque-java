package com.example.estoquejava.models;

import com.example.estoquejava.repository.UsuarioRepositorio;

public class UsuarioController {

    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioController() {
        this.usuarioRepositorio = UsuarioRepositorio.getInstance();
    }



    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        usuarioRepositorio.adicionarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        Usuario usuario = usuarioRepositorio.buscarUsuarioPorId(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
        }
        return usuario;
    }

    public Usuario buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioRepositorio.buscarUsuarioPorNome(nome);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário com o nome especificado não encontrado.");
        }
        return usuario;
    }

    public void removerUsuario(int id) {
        buscarUsuarioPorId(id); // Valida se o usuário existe
        usuarioRepositorio.removerUsuario(id);
    }

    public void atualizarUsuario(Usuario usuario) {
        buscarUsuarioPorId(usuario.getId()); // Valida se o usuário existe
        usuarioRepositorio.atualizarUsuario(usuario);
    }

    public Usuario[] listarUsuarios() {
        return usuarioRepositorio.listarUsuarios();
    }
}
