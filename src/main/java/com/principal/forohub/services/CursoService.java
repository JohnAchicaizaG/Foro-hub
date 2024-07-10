package com.principal.forohub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.course.CourseRequestDTO;
import com.principal.forohub.dto.course.CourseResponseDTO;
import com.principal.forohub.entities.CategoriaEntitys;
import com.principal.forohub.entities.CursoEntity;
import com.principal.forohub.repositories.CategoriaRepository;
import com.principal.forohub.repositories.CursoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoService implements IServices<CourseRequestDTO, CourseResponseDTO> {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CourseResponseDTO actualizarTopico(Long id, CourseRequestDTO nuevosDatos) {
        CursoEntity cursoEntity = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        CategoriaEntitys categoria = categoriaRepository.findById(nuevosDatos.getCategoria().getId()).orElseThrow(() -> new EntityNotFoundException());
        cursoEntity.setCategoria(categoria);
        cursoEntity.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(cursoRepository.save(cursoEntity), CourseResponseDTO.class);
    }

    @Override
    public void eliminarCategoriaTopic(Long id) {
        CursoEntity cursoEntity = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        cursoRepository.delete(cursoEntity);
    }

    @Override
    public CourseResponseDTO encontrarCategoriaTopic(Long id) {
        CursoEntity cursoEntityEncontrado = cursoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(cursoEntityEncontrado, CourseResponseDTO.class);
    }

    @Override
    public CourseResponseDTO guardarCategoriaTopic(CourseRequestDTO nuevoRegistro) {
        CursoEntity cursoEntityNuevo = modelMapper.map(nuevoRegistro, CursoEntity.class);
        return modelMapper.map(cursoRepository.save(cursoEntityNuevo), CourseResponseDTO.class);
    }

    @Override
    public List<CourseResponseDTO> listarTopic() {
        List<CursoEntity> cursoEntities = cursoRepository.findAll();
        List<CourseResponseDTO> courseResponseDTOS = cursoEntities.stream().map(c -> modelMapper.map(c, CourseResponseDTO.class)).toList();
        return courseResponseDTOS;
    }


    



}
