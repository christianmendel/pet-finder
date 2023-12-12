package com.example.petfinder.mapper;

import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.security.model.Usuario;
import org.modelmapper.ModelMapper;


public class CadastroMapper {
    public static Usuario toEntity(CadastroRequest request) {
        Usuario usuario = new ModelMapper().map(request, Usuario.class);
        return usuario;
    }


    public static CadastroResponse toResponse(Usuario usuario) {
        return CadastroResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .apelido(usuario.getApelido())
                .data_de_nascimento(usuario.getData_de_nascimento())
                .foto_perfil(usuario.getFoto_perfil())
                .permissoes(usuario.getPermissoes())
                .build();
    }
}
