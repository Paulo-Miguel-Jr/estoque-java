package com.example.estoquejava.gui;

import com.example.estoquejava.models.Fachada;
import com.example.estoquejava.models.Usuario;
import com.example.estoquejava.models.enums.TipoUsuario;

public class Main {

    public static void main(String[] args){

        Usuario usuario = new Usuario("Wendell", "123", 1, TipoUsuario.USUARIO);
        Fachada.getInstacia().cadastrarUsuario(usuario);
    }
}
