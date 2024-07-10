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

import com.principal.forohub.dto.users.UsersRequestDTO;
import com.principal.forohub.dto.users.UsersResponseDTO;
import com.principal.forohub.services.UserService;
@RestController
@RequestMapping("/usuario")
public class UserController implements IControllers<UsersRequestDTO, UsersResponseDTO> {

    @Autowired
    private UserService userService;

    @PutMapping("/update/{id}")
    public ResponseEntity<UsersResponseDTO> actualizarUsuario(Long id, UsersRequestDTO nuevosDatos) {
        return new ResponseEntity<>(userService.actualizarTopico(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UsersResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(userService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<UsersResponseDTO> guardar(UsersRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(userService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<UsersResponseDTO>> mostrar() {
        return new ResponseEntity<>(userService.listarTopic(), HttpStatus.OK);
    }
}
