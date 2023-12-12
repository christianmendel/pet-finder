package com.example.petfinder.mapper;


import com.example.petfinder.controller.response.UsuarioResponse;
import com.example.petfinder.security.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static List<UsuarioResponse> toResponse(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(usuario -> toResponse(usuario))
                .collect(Collectors.toList());
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .apelido(usuario.getApelido())
                .data_de_nascimento(usuario.getData_de_nascimento())
                .foto_perfil(usuario.getFoto_perfil())
                //.amizades(usuario.getAmizades())
                //.postagens(usuario.getPostagens())
                .build();

    }

    public static Page<UsuarioResponse> toResponseList(List<Usuario> usuarios, Pageable pageable) {
        List<UsuarioResponse> lista = usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());

        return new PageImpl<UsuarioResponse>(lista, pageable, lista.size());
    }


}


