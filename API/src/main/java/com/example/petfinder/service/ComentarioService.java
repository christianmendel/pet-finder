package com.example.petfinder.service;

import com.example.petfinder.controller.request.ComentarioRequest;
import com.example.petfinder.controller.request.PostagemRequest;
import com.example.petfinder.controller.response.ListarPostagemResponse;
import com.example.petfinder.mapper.CadastroMapper;
import com.example.petfinder.mapper.ComentarioMapper;
import com.example.petfinder.mapper.ListarPostagemMapper;
import com.example.petfinder.mapper.PostagemMapper;
import com.example.petfinder.model.Comentario;
import com.example.petfinder.model.Postagem;
import com.example.petfinder.model.Status;
import com.example.petfinder.repository.ComentarioRepository;
import com.example.petfinder.repository.PostagemRepository;
import com.example.petfinder.security.model.Usuario;
import com.example.petfinder.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public void adicinarComentario(ComentarioRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        Postagem postagem = postagemRepository.findById(request.getId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel encontrar essa postagem."));

        Comentario comentario = ComentarioMapper.toEntity(request);
        comentario.setUsuario(usuarioLogado);
        comentario.setHorario(LocalDateTime.now());
        comentario.setPostagem(postagem);

        comentarioRepository.save(comentario);
    }

    public void deletarComentarioPostagemID(Long idPostagem, Long idComentario) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        Postagem postagem = postagemRepository.findById(idPostagem)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel encontrar essa postagem."));

        Comentario comentario = comentarioRepository.findById(idComentario)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel encontrar esse comentario."));

        Comentario comentario_postagem = comentarioRepository.findByIdAndPostagemId(idComentario, idPostagem);


        if(comentario_postagem.getUsuario().equals(usuarioLogado)){
            comentarioRepository.delete(comentario_postagem);
        }

        if(postagem.getUsuario().equals(usuarioLogado)){
            comentarioRepository.delete(comentario_postagem);
        }
    }

}
