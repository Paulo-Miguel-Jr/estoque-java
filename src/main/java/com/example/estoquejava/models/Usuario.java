package com.example.estoquejava.models;

import com.example.estoquejava.models.enums.TipoUsuario;

public class Usuario {
    private String nome;
    private String senha;
    private int id;
//    private TipoUsuario tipo;

    public Usuario(String nome, String senha, int id) {
        setNome(nome);
        setSenha(senha);
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        verificarNomeVazio(nome);
        verificarNomeCaracteres(nome);
        this.nome = nome;
    }
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        verificaSenhaCaracteres(senha);
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private static void verificarNomeCaracteres(String nome) {
        if (nome.length() < 3 || nome.length() > 50) {
            throw new IllegalArgumentException("O nome deve ter entre 3 e 50 caracteres.");
        }
    }

    private static void verificarNomeVazio(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
    }

    private static void verificaSenhaCaracteres(String senha) {
        if (senha == null || senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres.");
        }
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", id=" + id +
                '}';
    }
}
