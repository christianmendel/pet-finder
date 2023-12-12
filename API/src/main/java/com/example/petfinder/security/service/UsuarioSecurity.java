package com.example.petfinder.security.service;

import com.example.petfinder.security.model.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioSecurity implements UserDetails {

    private Long id;
    private String email;
    private String senha;
    private List<SimpleGrantedAuthority> permissoes;
    private String apelido;
    private LocalDate data_de_nascimento;
    private String foto_perfil;

    public UsuarioSecurity(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.permissoes = convertePermissoes(usuario);
        this.apelido = usuario.getApelido();
        this.data_de_nascimento = usuario.getData_de_nascimento();
        this.foto_perfil = usuario.getFoto_perfil();
    }

    private List<SimpleGrantedAuthority> convertePermissoes(Usuario usuario) {
        return usuario.getPermissoes().stream()
                .map(permissao -> new SimpleGrantedAuthority("ROLE_" + permissao.getNome()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissoes;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
