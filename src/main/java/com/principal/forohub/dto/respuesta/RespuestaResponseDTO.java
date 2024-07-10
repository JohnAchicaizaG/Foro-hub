package com.principal.forohub.dto.respuesta;

import java.time.LocalDateTime;

import com.principal.forohub.entities.UsuarioEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaResponseDTO {

    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private UsuarioEntity autor;
    

}
