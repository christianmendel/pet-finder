package com.example.petfinder.security.controller;


import com.example.petfinder.controller.response.LoginResponse;
import com.example.petfinder.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UsuarioAutenticadoService usuarioAutenticadoService;

    @PostMapping
    public LoginResponse login() {
        LoginResponse response = new LoginResponse();
        response.setUsuarioId(usuarioAutenticadoService.getId());
        return response;
    }
}
