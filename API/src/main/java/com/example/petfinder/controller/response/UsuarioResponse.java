package com.example.petfinder.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UsuarioResponse {

    private Long id;

    private String nome;

    private String email;

    private String apelido;

    private LocalDate data_de_nascimento;

    private String foto_perfil;
}
