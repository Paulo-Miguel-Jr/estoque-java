package com.example.estoquejava.repository.interfaces;

import com.example.estoquejava.models.Usuario;

public interface IUsuarioRepositorio {
    void adicionarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int id);
    Usuario buscarUsuarioPorNome(String nome);
    void removerUsuario(int id);
    void atualizarUsuario(Usuario usuario);
    Usuario[] listarUsuarios();
}
