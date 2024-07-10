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

import com.principal.forohub.dto.category.CategoryRequestDTO;
import com.principal.forohub.dto.category.CategoryResponseDTO;
import com.principal.forohub.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoryController implements IControllers<CategoryRequestDTO, CategoryResponseDTO> {

    @Autowired
    private CategoriaService categoriaService;

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDTO> actualizarUsuario(Long id, CategoryRequestDTO nuevosDatos) {
        return new ResponseEntity<>(categoriaService.actualizarTopico(id, nuevosDatos), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        categoriaService.eliminarCategoriaTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CategoryResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(categoriaService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDTO> guardar(CategoryRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(categoriaService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.OK);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<CategoryResponseDTO>> mostrar() {
        return new ResponseEntity<>(categoriaService.listarTopic(), HttpStatus.OK);
    }

}
