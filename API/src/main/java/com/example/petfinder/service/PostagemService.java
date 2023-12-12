package com.example.petfinder.service;

import com.example.petfinder.controller.request.PostagemRequest;
import com.example.petfinder.controller.response.ListarPostagemResponse;
import com.example.petfinder.mapper.ListarPostagemMapper;
import com.example.petfinder.mapper.PostagemMapper;
import com.example.petfinder.model.Postagem;
import com.example.petfinder.model.Status;
import com.example.petfinder.repository.PostagemRepository;
import com.example.petfinder.security.model.Usuario;
import com.example.petfinder.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostagemService {

    @Autowired
    PostagemRepository postagemRepository;

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public void criar(PostagemRequest request) {
        Usuario usuarioLogado = usuarioAutenticadoService.get();

        Postagem postagem = PostagemMapper.toEntity(request);
        postagem.setUsuario(usuarioLogado);
        postagem.setData(LocalDate.now());
        postagem.setStatus(Status.PERDIDO);

        postagemRepository.save(postagem);
    }

    public List<ListarPostagemResponse> listar() {
        List<Postagem> postagens= postagemRepository.findAll();

        return ListarPostagemMapper.toResponseList(postagens);
    }

    public List<ListarPostagemResponse> listarPerdios() {
       List<Postagem> postagens= postagemRepository.findAllByStatus(Status.PERDIDO);

       return ListarPostagemMapper.toResponseList(postagens);
    }

    public List<ListarPostagemResponse> listarPostagensUsuarioLogado() {
        Usuario usuarioLogado = usuarioAutenticadoService.get();


        List<Postagem> postagem= postagemRepository.findByUsuarioId(usuarioLogado.getId());

        return  ListarPostagemMapper.toResponseList(postagem);


    }

    public List<ListarPostagemResponse> listarPorCidadeEstado(String cidade, String estado) {
        List<Postagem> postagens= postagemRepository.findAllByCidadeAndEstado(cidade,estado);

        return ListarPostagemMapper.toResponseList(postagens);
    }

    public ListarPostagemResponse listarPostagemID(Long id) {
        Postagem postagem= postagemRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Não foi possivel encontrar essa postagem."));

        return ListarPostagemMapper.toResponse(postagem);
    }

    public void alterarStatusPostagemID(Long id) {

        Usuario usuarioLogado = usuarioAutenticadoService.get();


        Postagem postagem= postagemRepository.findByIdAndUsuarioId(id, usuarioLogado.getId());

        postagem  = ListarPostagemMapper.toEntity(postagem);
        postagem.setStatus(Status.ENCONTRADO);

        postagemRepository.save(postagem);
    }

    public void deletarPostagemID(Long id) {

        Usuario usuarioLogado = usuarioAutenticadoService.get();


        Postagem postagem= postagemRepository.findByIdAndUsuarioId(id, usuarioLogado.getId());

        postagemRepository.delete(postagem);
    }

}
