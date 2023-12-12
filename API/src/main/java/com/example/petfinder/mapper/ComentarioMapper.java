package com.example.petfinder.mapper;

import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.request.ComentarioRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.controller.response.ComentarioResponse;
import com.example.petfinder.controller.response.UsuarioResponse;
import com.example.petfinder.model.Comentario;
import com.example.petfinder.security.model.Usuario;
import com.example.petfinder.service.ComentarioService;
import org.modelmapper.ModelMapper;


public class ComentarioMapper {
    public static Comentario toEntity(ComentarioRequest request) {
        Comentario comentario = new ModelMapper().map(request, Comentario.class);
        return comentario;
    }

    public static ComentarioResponse toResponse(Comentario comentario) {
        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(comentario.getUsuario());

        return ComentarioResponse.builder()
                .id(comentario.getId())
                .comentario(comentario.getComentario())
                .horario(comentario.getHorario())
                .usuario(usuarioResponse)
                .build();

    }

}
