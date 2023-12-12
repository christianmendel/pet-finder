package com.example.petfinder.mapper;

import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.request.PostagemRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.model.Postagem;
import com.example.petfinder.security.model.Usuario;
import org.modelmapper.ModelMapper;

public class PostagemMapper {
    public static Postagem toEntity(PostagemRequest request) {
        Postagem postagem = new ModelMapper().map(request, Postagem.class);
        return postagem;
    }
}
