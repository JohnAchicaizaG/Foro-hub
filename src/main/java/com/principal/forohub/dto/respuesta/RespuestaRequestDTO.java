package com.principal.forohub.dto.respuesta;

import com.principal.forohub.entities.TopicoEntity;
import com.principal.forohub.entities.UsuarioEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaRequestDTO {

    @NotBlank
    private String mensaje;
    @NotNull
    private TopicoEntity topicoEntity;
    @NotNull
    private UsuarioEntity autor;

}
