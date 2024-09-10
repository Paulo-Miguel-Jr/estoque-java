package com.example.estoquejava.repository;

import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.repository.interfaces.IUsuarioRepositorio;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UsuarioRepositorio implements IUsuarioRepositorio, Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MAX_USUARIOS = 100;
    private static UsuarioRepositorio singletonUsuRep;
    private Map<Integer, Usuario> usuarios; // armazenar usuários por ID
    private int nextId;
    private static final String FILE_NAME = "usuarios.dat";

    private UsuarioRepositorio() {
        usuarios = new HashMap<>();
        nextId = 2; // Começa com o ID 2 e aumenta
    }

    public static UsuarioRepositorio getInstance() {
        if (singletonUsuRep == null) {
            singletonUsuRep = new UsuarioRepositorio();
            singletonUsuRep.load(); //carrega os dados do arquivo ao criar a instância
        }
        return singletonUsuRep;
    }

    @Override
    public void adicionarUsuario(Usuario usuario) {
        validarUsuario(usuario);
        usuarios.put(usuario.getId(), usuario);
        save(); //salva após adicionar um usuário
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
            save(); //salva após remover um usuário
        } else {
            throw new IllegalArgumentException("Usuário com o ID especificado não encontrado.");
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.getId())) {
            usuarios.put(usuario.getId(), usuario);
            save(); //salva após atualizar um usuário
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
        } while (usuarios.containsKey(id)); // Garante que o ID não esteja em uso
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

    //salva o estado do repositório em um arquivo
    private void save() {
        try (ObjectOutputStream salvarArquivo = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            salvarArquivo.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //carrega o estado do repositório a partir de um arquivo
    private void load() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream carregarArquivo = new ObjectInputStream(new FileInputStream(file))) {
                UsuarioRepositorio loadedRepo = (UsuarioRepositorio) carregarArquivo.readObject();
                this.usuarios = loadedRepo.usuarios;
                this.nextId = loadedRepo.nextId;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
