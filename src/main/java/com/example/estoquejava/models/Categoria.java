package com.example.estoquejava.models;

import java.util.ArrayList;
import java.util.List;
public class Categoria {

    private String nome;
    private int id;
    private List<Produto> produtos = new ArrayList<Produto>();

    public Categoria(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public Categoria() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome=" + nome +
                ", id=" + id +
                '}';
    }

}
