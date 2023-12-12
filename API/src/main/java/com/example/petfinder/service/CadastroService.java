package com.example.petfinder.service;


import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.mapper.CadastroMapper;
import com.example.petfinder.security.model.Permissao;
import com.example.petfinder.security.model.Usuario;
import com.example.petfinder.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CadastroService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CadastroResponse cadastrarUsuario(CadastroRequest request) {

        Usuario usuario = CadastroMapper.toEntity(request);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Permissao permissao = new Permissao();
        permissao.setNome("User");

        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(permissao);

        usuario.setPermissoes(permissoes);


        usuarioRepository.save(usuario);
        return CadastroMapper.toResponse(usuario);
    }
}
