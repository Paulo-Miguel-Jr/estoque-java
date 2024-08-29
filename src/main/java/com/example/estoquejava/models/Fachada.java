package com.example.estoquejava.models;

public class Fachada {

    private static Fachada instacia;

    private UsuarioCadastro usuarioCadastro;



    public static Fachada getInstacia() {
        if(instacia == null){
        instacia = new Fachada();
        }
        return instacia;

    }

    public void cadastrarUsuario(Usuario usuario){
        usuarioCadastro.cadastrarUsuario(usuario);
    }

}
