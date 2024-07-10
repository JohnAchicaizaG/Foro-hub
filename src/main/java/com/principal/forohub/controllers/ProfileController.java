package com.principal.forohub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.principal.forohub.dto.profile.ProfileRequestDTO;
import com.principal.forohub.dto.profile.ProfileResponseDTO;
import com.principal.forohub.services.PerfilService;
@RestController
@RequestMapping("/perfil")
public class ProfileController implements IControllers<ProfileRequestDTO, ProfileResponseDTO> {

    @Autowired
    private PerfilService perfilService;

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileResponseDTO> actualizarUsuario(Long id, ProfileRequestDTO nuevosDatos) {
        return new ResponseEntity<>(perfilService.actualizarTopico(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        perfilService.eliminarCategoriaTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfileResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(perfilService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> guardar(ProfileRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(perfilService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProfileResponseDTO>> mostrar() {
        return new ResponseEntity<>(perfilService.listarTopic(), HttpStatus.OK);
    }
}
