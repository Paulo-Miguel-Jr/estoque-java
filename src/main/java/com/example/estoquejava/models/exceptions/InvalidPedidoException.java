package com.example.estoquejava.models.exceptions;

public class InvalidPedidoException extends RuntimeException {
    public InvalidPedidoException (String message) {
        super(message);
    }
}
