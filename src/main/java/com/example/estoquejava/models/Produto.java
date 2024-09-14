package com.example.estoquejava.models;


import java.io.Serializable;

public class Produto implements Serializable {

    private static final long serialVersionUID = 2L;

    private String nome;
    private int id;
    private double preco;
    private double quantidade;
    private String unidadeDeMedida;
    private double estoqueMinimo;



    public Produto(String nome, int id, double preco, double quantidade, String unidadeDeMedida, double estoqueMinimo) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.quantidade = quantidade;
        this.unidadeDeMedida = unidadeDeMedida;
        this.estoqueMinimo = estoqueMinimo;
    }

    public Produto() {}

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", unidadeDeMedida='" + unidadeDeMedida + '\'' +
                ", estoqueMinimo=" + estoqueMinimo +
                '}';
    }

}
