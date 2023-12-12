package com.example.petfinder.repository;

import com.example.petfinder.model.Comentario;
import com.example.petfinder.model.Postagem;
import com.example.petfinder.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository <Comentario,Long> {

     Comentario findByIdAndPostagemId(Long idComentario, Long idPostagem);
}
