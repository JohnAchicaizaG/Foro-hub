package com.principal.forohub.dto.topic;

import com.principal.forohub.entities.CursoEntity;
import com.principal.forohub.entities.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicRequestDTO {

    private String titulo;
    private String mensaje;
    private UsuarioEntity autor;
    private CursoEntity cursoEntity;

}
