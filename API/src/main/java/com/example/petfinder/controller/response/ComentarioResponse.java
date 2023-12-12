package com.example.petfinder.controller.response;

import com.example.petfinder.model.Postagem;
import com.example.petfinder.model.Status;
import com.example.petfinder.model.Tipo;
import com.example.petfinder.security.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ComentarioResponse {
    private Long id;

    private String comentario;

    private LocalDateTime horario;

    private UsuarioResponse usuario;

}
