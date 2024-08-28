package com.example.estoquejava.repository;

import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.interfaces.IUsuarioRepositorio;

public class UsuarioRepositorio implements IUsuarioRepositorio {
    private static final int MAX_USUARIOS = 100; // Define um tamanho máximo para o array
    private Usuario[] usuarios;
    private int count;

    public UsuarioRepositorio() {
        usuarios = new Usuario[MAX_USUARIOS];
        count = 0;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        if (count >= MAX_USUARIOS) {
            throw new IllegalStateException("Número máximo de usuários atingido.");
        }
        if (buscarUsuarioPorId(usuario.getId()) != null) {
            throw new IllegalArgumentException("Usuário com o mesmo ID já existe.");
        }
        if (buscarUsuarioPorNome(usuario.getNome()) != null) {
            throw new IllegalArgumentException("Usuário com o mesmo nome já existe.");
        }
        usuarios[count++] = usuario;
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        for (int i = 0; i < count; i++) {
            if (usuarios[i].getId() == id) {
                return usuarios[i];
            }
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioPorNome(String nome) {
        for (int i = 0; i < count; i++) {
            if (usuarios[i].getNome().equalsIgnoreCase(nome)) {
                return usuarios[i];
            }
        }
        return null;
    }

    @Override
    public void removerUsuario(int id) {
        for (int i = 0; i < count; i++) {
            if (usuarios[i].getId() == id) {
                //move os elementos restantes para preencher o espaço
                for (int j = i; j < count - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[--count] = null; //remove a última referência
                return;
            }
        }
        throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
    }

    @Override
    public Usuario[] listarUsuarios() {
        Usuario[] resultado = new Usuario[count];
        System.arraycopy(usuarios, 0, resultado, 0, count);
        return resultado;
    }
}
