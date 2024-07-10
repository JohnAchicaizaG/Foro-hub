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

import com.principal.forohub.dto.course.CourseRequestDTO;
import com.principal.forohub.dto.course.CourseResponseDTO;
import com.principal.forohub.services.CursoService;

@RestController
@RequestMapping("/curso")
public class CourseController implements IControllers<CourseRequestDTO, CourseResponseDTO> {


    @Autowired
    private CursoService cursoService;

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseResponseDTO> actualizarUsuario(Long id, CourseRequestDTO nuevosDatos) {
        return new ResponseEntity<>(cursoService.actualizarTopico(id, nuevosDatos), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        cursoService.eliminarCategoriaTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CourseResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(cursoService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CourseResponseDTO> guardar(CourseRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(cursoService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.CREATED);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CourseResponseDTO>> mostrar() {
        return new ResponseEntity<>(cursoService.listarTopic(), HttpStatus.OK);
    }

}
