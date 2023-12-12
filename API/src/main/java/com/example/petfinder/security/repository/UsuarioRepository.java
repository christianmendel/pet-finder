package com.example.petfinder.security.repository;

import com.example.petfinder.security.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE ?1% OR u.email LIKE ?2%")
    Page<Usuario> findByEmailAndNome(String email, String nome, Pageable pageable);

    Page<Usuario> findAllByIdIn(List<Long> amigoIds, Pageable pageable);
}
