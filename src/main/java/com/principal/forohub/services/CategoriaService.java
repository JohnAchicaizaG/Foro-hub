package com.principal.forohub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.category.CategoryRequestDTO;
import com.principal.forohub.dto.category.CategoryResponseDTO;
import com.principal.forohub.entities.CategoriaEntitys;
import com.principal.forohub.repositories.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService implements IServices<CategoryRequestDTO, CategoryResponseDTO>{

    @Autowired
    private CategoriaRepository categoriaRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public CategoryResponseDTO actualizarTopico(Long id, CategoryRequestDTO nuevosDatos) {
        CategoriaEntitys categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        categoria.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(categoriaRepository.save(categoria), CategoryResponseDTO.class);
    }

    @Override
    public void eliminarCategoriaTopic(Long id) {
        CategoriaEntitys categoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        categoriaRepository.delete(categoria);
    
    }

    @Override
    public CategoryResponseDTO encontrarCategoriaTopic(Long id) {
        CategoriaEntitys categoriaEncontrada = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(categoriaEncontrada, CategoryResponseDTO.class);
    }

    @Override
    public CategoryResponseDTO guardarCategoriaTopic(CategoryRequestDTO nuevoRegistro) {
        CategoriaEntitys nuevaCategoria = modelMapper.map(nuevoRegistro, CategoriaEntitys.class);
        CategoryResponseDTO categoryResponseDTO = modelMapper.map(categoriaRepository.save(nuevaCategoria), CategoryResponseDTO.class);
        return categoryResponseDTO;
    }

    @Override
    public List<CategoryResponseDTO> listarTopic() {
        List<CategoriaEntitys> lista = categoriaRepository.findAll();
        List<CategoryResponseDTO> listaCategoryResponseDTOS = lista.stream().map(c -> modelMapper.map(c, CategoryResponseDTO.class)).toList();
        return listaCategoryResponseDTOS;
    }

}
