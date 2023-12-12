package com.example.petfinder.repository;

import com.example.petfinder.model.Postagem;
import com.example.petfinder.model.Status;
import com.example.petfinder.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostagemRepository extends JpaRepository <Postagem,Long> {
    List<Postagem> findAllByStatus(Status perdido);

    List<Postagem> findAllByCidadeAndEstado(String cidade, String estado);

    Postagem findByIdAndUsuarioId(Long id, Long usuarioLogado);

    List<Postagem> findByUsuarioId(Long id);
}
