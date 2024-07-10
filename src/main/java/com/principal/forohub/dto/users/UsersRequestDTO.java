package com.principal.forohub.dto.users;

import com.principal.forohub.entities.PerfilEntity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersRequestDTO {

    @NotNull
    private String nombre;
    @Email
    private String correoElectronico;
    @NotNull
    @NotBlank
    private String contrasena;
    @NotNull
    private PerfilEntity perfilEntity;
}
