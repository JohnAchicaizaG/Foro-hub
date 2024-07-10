package com.principal.forohub.dto.users;

import com.principal.forohub.entities.PerfilEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponseDTO {

    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private PerfilEntity perfilEntity;
}
