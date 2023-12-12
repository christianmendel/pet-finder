package com.example.petfinder.controller.request;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ComentarioRequest {
    private Long id;
    private String comentario;
}
