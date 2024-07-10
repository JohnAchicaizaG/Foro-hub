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

import com.principal.forohub.dto.respuesta.RespuestaRequestDTO;
import com.principal.forohub.dto.respuesta.RespuestaResponseDTO;
import com.principal.forohub.services.RespuestaService;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController implements IControllers<RespuestaRequestDTO, RespuestaResponseDTO> {

    @Autowired
    private RespuestaService respuestaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<RespuestaResponseDTO> actualizarUsuario(Long id, RespuestaRequestDTO nuevosDatos) {
        return new ResponseEntity<>(respuestaService.actualizarTopico(id, nuevosDatos), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        respuestaService.eliminarCategoriaTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<RespuestaResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(respuestaService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<RespuestaResponseDTO> guardar(RespuestaRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(respuestaService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<RespuestaResponseDTO>> mostrar() {
        return new ResponseEntity<>(respuestaService.listarTopic(), HttpStatus.OK);
    }

    




}
