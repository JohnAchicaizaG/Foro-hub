package com.principal.forohub.services;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.respuesta.RespuestaRequestDTO;
import com.principal.forohub.dto.respuesta.RespuestaResponseDTO;
import com.principal.forohub.entities.RespuestaEntity;
import com.principal.forohub.entities.TopicoEntity;
import com.principal.forohub.entities.UsuarioEntity;
import com.principal.forohub.repositories.RespuestaRepository;
import com.principal.forohub.repositories.TopicoRepository;
import com.principal.forohub.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RespuestaService implements IServices<RespuestaRequestDTO, RespuestaResponseDTO> {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public RespuestaResponseDTO actualizarTopico(Long id, RespuestaRequestDTO nuevosDatos) {
        TopicoEntity topicoEntity = topicoRepository.findById(nuevosDatos.getTopicoEntity().getId()).orElseThrow(() -> new EntityNotFoundException());
        UsuarioEntity autor = userRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        RespuestaEntity respuestaEntity = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        respuestaEntity.setAutor(autor);
        respuestaEntity.setTopicoEntity(topicoEntity);
        respuestaEntity.setMensaje(nuevosDatos.getMensaje());
        return modelMapper.map(respuestaRepository.save(respuestaEntity), RespuestaResponseDTO.class);
    }

    public void eliminarCategoriaTopic(Long id) {
        RespuestaEntity respuestaEntity = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        respuestaRepository.delete(respuestaEntity);
    }

    public RespuestaResponseDTO encontrarCategoriaTopic(Long id) {
        RespuestaEntity respuestaEntity = respuestaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(respuestaEntity, RespuestaResponseDTO.class);
    }

    public RespuestaResponseDTO guardarCategoriaTopic(RespuestaRequestDTO nuevoRegistro) {
        TopicoEntity topicoEntity = topicoRepository.findById(nuevoRegistro.getTopicoEntity().getId()).orElseThrow(() -> new EntityNotFoundException());
        UsuarioEntity autor = userRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        RespuestaEntity respuestaEntity = modelMapper.map(nuevoRegistro, RespuestaEntity.class);
        respuestaEntity.setAutor(autor);
        respuestaEntity.setTopicoEntity(topicoEntity);
        respuestaEntity.setFechaCreacion(LocalDateTime.now());
        return modelMapper.map(respuestaRepository.save(respuestaEntity), RespuestaResponseDTO.class);
    }

    public List<RespuestaResponseDTO> listarTopic() {
        List<RespuestaEntity> respuestaEntities = respuestaRepository.findAll();
        List<RespuestaResponseDTO> respuestaResponseDTOs = respuestaEntities.stream().map(r -> modelMapper.map(r, RespuestaResponseDTO.class)).toList();
        return respuestaResponseDTOs;
    }
}

