package com.example.estoquejava.models;

import java.util.regex.Pattern;

public class Fornecedor {
    private String nome;
    private int id;
    private String cnpj;
    private String telefone;
    private String cep;
    private int numero;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    public Fornecedor(String nome, int id, String cnpj, String telefone, String cep, int numero,
                      String rua, String complemento, String bairro, String cidade, String estado) {
        this.setNome(nome);
        this.setId(id);
        this.setCnpj(cnpj);
        this.setTelefone(telefone);
        this.setCep(cep);
        this.setNumero(numero);
        this.setRua(rua);
        this.setComplemento(complemento);
        this.setBairro(bairro);
        this.setCidade(cidade);
        this.setEstado(estado);
    }

    public Fornecedor() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID deve ser positivo.");
        }
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (!Pattern.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }
        this.cnpj = cnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (!Pattern.matches("\\d{5}-\\d{4}", telefone)) {
            throw new IllegalArgumentException("Telefone inválido.");
        }
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (!Pattern.matches("\\d{5}-\\d{3}", cep)) {
            throw new IllegalArgumentException("CEP inválido.");
        }
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Número deve ser positivo.");
        }
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        if (rua == null || rua.isEmpty()) {
            throw new IllegalArgumentException("Rua não pode ser vazia.");
        }
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.isEmpty()) {
            throw new IllegalArgumentException("Bairro não pode ser vazio.");
        }
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isEmpty()) {
            throw new IllegalArgumentException("Cidade não pode ser vazia.");
        }
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.isEmpty()) {
            throw new IllegalArgumentException("Estado não pode ser vazio.");
        }
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", numero=" + numero +
                ", rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
