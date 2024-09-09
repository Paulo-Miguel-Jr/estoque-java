package com.example.estoquejava.repository.interfaces;

import com.example.estoquejava.models.Usuario;

public interface IUsuarioRepositorio {
    void adicionarUsuario(Usuario usuario);
    Usuario buscarUsuarioPorId(int id); // ID como int
    Usuario buscarUsuarioPorNome(String nome);
    void removerUsuario(int id); // ID como int
    void atualizarUsuario(Usuario usuario);
    Usuario[] listarUsuarios();
    int gerarIdUnico(); // Método para gerar IDs únicos
}
