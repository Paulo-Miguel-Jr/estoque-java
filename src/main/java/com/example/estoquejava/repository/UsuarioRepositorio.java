package com.example.estoquejava.repository;

import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.interfaces.IUsuarioRepositorio;

import java.util.HashMap;
import java.util.Map;

public class UsuarioRepositorio implements IUsuarioRepositorio {
    private static final int MAX_USUARIOS = 100;
    private static UsuarioRepositorio singletonUsuRep;
    private Map<Integer, Usuario> usuarios; // Armazenar usuários por ID
    private int nextId;

    public UsuarioRepositorio() {
        usuarios = new HashMap<>();
        nextId = 2; //começa com o ID 2 e aumenta
    }

    public static UsuarioRepositorio getInstance() {
        if (singletonUsuRep == null) {
            singletonUsuRep = new UsuarioRepositorio();
        }
        return singletonUsuRep;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarios.put(usuario.getId(), usuario);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.get(id);
    }

    @Override
    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void removerUsuario(int id) {
        if (usuarios.remove(id) != null) {
            //id removido com sucesso
        } else {
            throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            usuarios.put(usuario.getId(), usuario);
        } else {
            throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
        }
    }

    @Override
    public Usuario[] listarUsuarios() {
        return usuarios.values().toArray(new Usuario[0]);
    }

    @Override
    public int gerarIdUnico() {
        int id;
        do {
            id = nextId++;
        } while (usuarios.containsKey(id)); //Garante que o ID não esteja em uso
        return id;
    }

    private void validarUsuario(Usuario usuario) {
        if (usuarios.size() >= MAX_USUARIOS) {
            throw new IllegalStateException("Número máximo de usuários atingido.");
        }
        if (usuarios.containsKey(usuario.getId())) {
            throw new IllegalArgumentException("Usuário com o mesmo ID já existe.");
        }
        if (buscarUsuarioPorNome(usuario.getNome()) != null) {
            throw new IllegalArgumentException("Usuário com o mesmo nome já existe.");
        }
    }
}
