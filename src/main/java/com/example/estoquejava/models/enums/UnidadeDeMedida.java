package com.example.estoquejava.models.enums;

public enum UnidadeDeMedida {
    PACOTE("Pacote"),
    KG("Kg"),
    LITROS("Litros");

    private final String descricao;

    UnidadeDeMedida(String descricao) {
        this.descricao = descricao;
    }

    public static UnidadeDeMedida fromString(String text) {
        for (UnidadeDeMedida unidade : UnidadeDeMedida.values()) {
            if (unidade.descricao.equalsIgnoreCase(text)) {
                return unidade;
            }
        }
        throw new IllegalArgumentException("Unidade de medida inválida: " + text);
    }
}
