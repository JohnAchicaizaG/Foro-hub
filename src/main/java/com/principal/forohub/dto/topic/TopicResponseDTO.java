package com.principal.forohub.dto.topic;

import java.time.LocalDateTime;
import java.util.List;

import com.principal.forohub.entities.CursoEntity;
import com.principal.forohub.entities.RespuestaEntity;
import com.principal.forohub.entities.StatusEntity;
import com.principal.forohub.entities.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponseDTO {
    
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private StatusEntity statusEntity;
    private UsuarioEntity autor;
    private CursoEntity cursoEntity;
    private List<RespuestaEntity> respuestaEntities;

}
