package com.example.estoquejava.models.exceptions;

public class LimiteItensAlcancadoException extends RuntimeException {
    public LimiteItensAlcancadoException (String message){
        super(message);
    }
}
