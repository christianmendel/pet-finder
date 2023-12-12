package com.example.petfinder.controller.request;

import com.example.petfinder.model.Status;
import com.example.petfinder.model.Tipo;
import lombok.Data;

@Data
public class PostagemRequest {
    private Tipo tipo;

    private String descricao;

    private String cidade;

    private String estado;

    private String imagem;

}
