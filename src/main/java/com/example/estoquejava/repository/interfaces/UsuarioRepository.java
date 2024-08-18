package com.example.estoquejava.repository.interfaces;

import com.example.estoquejava.models.Usuario;

public interface UsuarioRepository {
    void adicionarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int id);
    Usuario buscarUsuarioPorNome(String nome);
    void removerUsuario(int id);
    Usuario[] listarUsuarios();
}

