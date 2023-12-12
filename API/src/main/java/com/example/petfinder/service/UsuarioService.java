package com.example.petfinder.service;


import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.controller.response.UsuarioResponse;
import com.example.petfinder.mapper.CadastroMapper;
import com.example.petfinder.mapper.UsuarioMapper;
import com.example.petfinder.security.model.Permissao;
import com.example.petfinder.security.model.Usuario;
import com.example.petfinder.security.repository.UsuarioRepository;
import com.example.petfinder.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    public UsuarioResponse listarPerfilLogado() {
        Usuario usuarioLogado = usuarioAutenticadoService.get();



        return UsuarioMapper.toResponse(usuarioLogado);
    }
}
