package com.example.petfinder.controller.response;

import com.example.petfinder.model.Status;
import com.example.petfinder.model.Tipo;
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
public class ListarPostagemResponse {
    private Long id;

    private Status status;

    private Tipo tipo;

    private String descricao;

    private LocalDate data;

    private String cidade;

    private String estado;

    private String imagem;

    private UsuarioResponse usuario;

    private List<ComentarioResponse> comentarios;
}
