package com.principal.forohub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.principal.forohub.dto.topic.TopicRequestDTO;
import com.principal.forohub.dto.topic.TopicResponseDTO;
import com.principal.forohub.services.TopicService;
@RestController
@RequestMapping("/topico")
public class TopicController implements IControllers<TopicRequestDTO, TopicResponseDTO>{

    @Autowired
    private TopicService topicService;

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicResponseDTO> actualizarUsuario(Long id, TopicRequestDTO nuevosDatos) {
        return new ResponseEntity<>(topicService.actualizarTopico(id, nuevosDatos), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminar(Long id) {
        topicService.eliminarCategoriaTopic(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<TopicResponseDTO> buscarporId(Long id) {
        return new ResponseEntity<>(topicService.encontrarCategoriaTopic(id), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<TopicResponseDTO> guardar(TopicRequestDTO nuevoRegistro) {
        return new ResponseEntity<>(topicService.guardarCategoriaTopic(nuevoRegistro), HttpStatus.CREATED);
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<TopicResponseDTO>> mostrar() {
        return new ResponseEntity<>(topicService.listarTopic(), HttpStatus.OK);
    }

    @GetMapping("/find/ten/asc")
    public ResponseEntity<List<TopicResponseDTO>> listarAsc() {
        return new ResponseEntity<>(topicService.listarAscendenteTopic(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<TopicResponseDTO>> listarPorCursoYAnio(@PageableDefault(size = 1) Pageable pageable, @RequestParam("curso") String curso, @RequestParam("anio") int anio) {
        return new ResponseEntity<>(topicService.listarCursoAnioTopic(pageable,curso, anio), HttpStatus.OK);
    }
    


}
