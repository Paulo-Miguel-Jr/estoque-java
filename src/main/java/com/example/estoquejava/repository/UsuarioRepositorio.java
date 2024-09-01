package com.example.estoquejava.repository;

import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.interfaces.IUsuarioRepositorio;

public class UsuarioRepositorio implements IUsuarioRepositorio {
    private static final int MAX_USUARIOS = 100;

    private static UsuarioRepositorio singletonUsuRep;
    private Usuario[] usuarios;
    private int count;



    public UsuarioRepositorio() {
        usuarios = new Usuario[MAX_USUARIOS];
        count = 0;
    }

    public static UsuarioRepositorio getInstance(){
        if (singletonUsuRep == null) {
            singletonUsuRep = new UsuarioRepositorio();
        }
        return singletonUsuRep;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        validarUsuario(usuario);
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
                for (int j = i; j < count - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[--count] = null;
                return;
            }
        }
        throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        for (int i = 0; i < count; i++) {
            if (usuarios[i].getId() == usuario.getId()) {
                usuarios[i] = usuario;
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

    private void validarUsuario(Usuario usuario) {
        if (count >= MAX_USUARIOS) {
            throw new IllegalStateException("Número máximo de usuários atingido.");
        }
        if (buscarUsuarioPorId(usuario.getId()) != null) {
            throw new IllegalArgumentException("Usuário com o mesmo ID já existe.");
        }
        if (buscarUsuarioPorNome(usuario.getNome()) != null) {
            throw new IllegalArgumentException("Usuário com o mesmo nome já existe.");
        }
    }
}
