package com.example.petfinder.controller.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CadastroRequest {
    private String nome;

    private String email;

    private String apelido;

    private LocalDate data_de_nascimento;

    private String foto_perfil;

    private String senha;
}
