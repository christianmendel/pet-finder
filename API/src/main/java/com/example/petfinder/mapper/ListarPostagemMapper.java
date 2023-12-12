package com.example.petfinder.mapper;


import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.response.ComentarioResponse;
import com.example.petfinder.controller.response.ListarPostagemResponse;
import com.example.petfinder.controller.response.UsuarioResponse;
import com.example.petfinder.model.Postagem;
import com.example.petfinder.security.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class ListarPostagemMapper {

    public static Postagem toEntity(Postagem request) {
        return new ModelMapper().map(request, Postagem.class);
    }

    public static List<ListarPostagemResponse> toResponseList(List<Postagem> postagens) {
        List<ListarPostagemResponse> lista = postagens.stream()
                .map(ListarPostagemMapper::toResponse)
                .collect(Collectors.toList());

        return lista;
    }

    public static ListarPostagemResponse toResponse(Postagem postagem) {
        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(postagem.getUsuario());

        List<ComentarioResponse> comentarios = postagem.getComentarios().stream()
                .map(ComentarioMapper::toResponse)
                .collect(Collectors.toList());

        return ListarPostagemResponse.builder()
                .id(postagem.getId())
                .data(postagem.getData())
                .descricao(postagem.getDescricao())
                .imagem(postagem.getImagem())
                .cidade(postagem.getCidade())
                .estado(postagem.getEstado())
                .tipo(postagem.getTipo())
                .status(postagem.getStatus())
                .usuario(usuarioResponse)
                .comentarios(comentarios)
                .build();
    }

}


