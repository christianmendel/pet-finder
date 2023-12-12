package com.example.petfinder.controller.response;

import com.example.petfinder.security.model.Permissao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CadastroResponse {
    private Long id;

    private String nome;

    private String email;

    private String apelido;

    private LocalDate data_de_nascimento;

    private String foto_perfil;

    private List<Permissao> permissoes;
}
