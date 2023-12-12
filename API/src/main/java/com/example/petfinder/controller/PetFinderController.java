package com.example.petfinder.controller;

import com.example.petfinder.controller.request.ComentarioRequest;
import com.example.petfinder.controller.request.PostagemRequest;
import com.example.petfinder.controller.response.ListarPostagemResponse;
import com.example.petfinder.controller.response.UsuarioResponse;
import com.example.petfinder.security.repository.UsuarioRepository;
import com.example.petfinder.security.service.UsuarioAutenticadoService;
import com.example.petfinder.service.ComentarioService;
import com.example.petfinder.service.PostagemService;
import com.example.petfinder.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/petfinder")
public class PetFinderController {

    @Autowired
    PostagemService postagemService;

    @Autowired
    ComentarioService comentarioService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/criar/postagem")
    public void criarPostagem(@RequestBody PostagemRequest request){
        postagemService.criar(request);
    }

    @PostMapping("/adicionar/comentario")
    public void adicionarComentario(@RequestBody ComentarioRequest request){
        comentarioService.adicinarComentario(request);
    }

    @GetMapping("/listar/postagens/perdidos")
    public List<ListarPostagemResponse> listarPostagem(){
       return postagemService.listarPerdios();
    }

    @GetMapping("/listar/postagens")
    public List<ListarPostagemResponse> listar(){
        return postagemService.listar();
    }

    @GetMapping("/listar/postagens/usuario")
    public List<ListarPostagemResponse> listarPostagensUsuarioLogado(){
        return postagemService.listarPostagensUsuarioLogado();
    }

    @GetMapping("/listar/postagens/{cidade}/{estado}")
    public List<ListarPostagemResponse> listarPostagemPorCidadeEstado(@PathVariable String cidade, @PathVariable String estado){

        return postagemService.listarPorCidadeEstado(cidade,estado);
    }

    @GetMapping("/listar/postagem/{id}")
    public ListarPostagemResponse listarPostagem(@PathVariable Long id){
        return postagemService.listarPostagemID(id);
    }

    @GetMapping("/listar/perfil/logado")
    public UsuarioResponse listarPerfilLogado(){
        return usuarioService.listarPerfilLogado();
    }

    @PutMapping("/alterar/postagem/{id}/status")
    public void alterarStatus(@PathVariable Long id){
        postagemService.alterarStatusPostagemID(id);
    }

    @DeleteMapping("/deletar/postagem/{id}")
    public void deletarPostagemID(@PathVariable Long id){
        postagemService.deletarPostagemID(id);
    }

    @DeleteMapping("/deletar/postagem/{idPostagem}/comentario/{idComentario}")
    public void deletarComentarioPostagemID(@PathVariable Long idPostagem, @PathVariable Long idComentario){
        comentarioService.deletarComentarioPostagemID(idPostagem, idComentario);
    }
}
