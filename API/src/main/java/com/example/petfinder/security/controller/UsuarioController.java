package com.example.petfinder.security.controller;

import com.example.petfinder.controller.request.CadastroRequest;
import com.example.petfinder.controller.response.CadastroResponse;
import com.example.petfinder.security.repository.UsuarioRepository;
import com.example.petfinder.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CadastroService cadastroService;

    @PostMapping
    public CadastroResponse incluir(@RequestBody CadastroRequest request) {
        return cadastroService.cadastrarUsuario(request);
    }
}
