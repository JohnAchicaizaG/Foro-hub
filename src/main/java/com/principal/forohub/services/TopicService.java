package com.principal.forohub.services;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.topic.TopicRequestDTO;
import com.principal.forohub.dto.topic.TopicResponseDTO;
import com.principal.forohub.entities.CursoEntity;
import com.principal.forohub.entities.StatusEntity;
import com.principal.forohub.entities.TopicoEntity;
import com.principal.forohub.entities.UsuarioEntity;
import com.principal.forohub.repositories.CursoRepository;
import com.principal.forohub.repositories.TopicoRepository;
import com.principal.forohub.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class TopicService implements IServices<TopicRequestDTO, TopicResponseDTO> {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public TopicResponseDTO actualizarTopico(Long id, TopicRequestDTO nuevosDatos) {
        UsuarioEntity autor = userRepository.findById(nuevosDatos.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        CursoEntity cursoEntity = cursoRepository.findById(nuevosDatos.getCursoEntity().getId()).orElseThrow(() -> new EntityNotFoundException());
        TopicoEntity topicoEntityActualizado = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        topicoEntityActualizado.setTitulo(nuevosDatos.getTitulo());
        topicoEntityActualizado.setMensaje(nuevosDatos.getMensaje());
        topicoEntityActualizado.setAutor(autor);
        topicoEntityActualizado.setCursoEntity(cursoEntity);
        return modelMapper.map(topicoRepository.save(topicoEntityActualizado), TopicResponseDTO.class);
    }

    public void eliminarCategoriaTopic(Long id) {
        TopicoEntity topicoEntity = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        topicoRepository.delete(topicoEntity);
    }

    public TopicResponseDTO encontrarCategoriaTopic(Long id) {
        TopicoEntity topicoEntity = topicoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(topicoEntity, TopicResponseDTO.class);
    }

    public TopicResponseDTO guardarCategoriaTopic(TopicRequestDTO nuevoRegistro) {
        UsuarioEntity autor = userRepository.findById(nuevoRegistro.getAutor().getId()).orElseThrow(() -> new EntityNotFoundException());
        CursoEntity cursoEntity = cursoRepository.findById(nuevoRegistro.getCursoEntity().getId()).orElseThrow(() -> new EntityNotFoundException());
        TopicoEntity topicoEntityNuevo = modelMapper.map(nuevoRegistro, TopicoEntity.class);
        topicoEntityNuevo.setStatusEntity(StatusEntity.ACTIVO);
        topicoEntityNuevo.setFechaCreacion(LocalDateTime.now());
        topicoEntityNuevo.setAutor(autor);
        topicoEntityNuevo.setCursoEntity(cursoEntity);
        return modelMapper.map(topicoRepository.save(topicoEntityNuevo), TopicResponseDTO.class);
    }

    public List<TopicResponseDTO> listarTopic() {
        // List<Topico> topicos = topicoRepository.encontrarTopicosActivos(Status.ACTIVO);
        List<TopicoEntity> topicoEntities = topicoRepository.findAll();
        List<TopicResponseDTO> listaTopicResponseDTOS = topicoEntities.stream().map(t -> modelMapper.map(t, TopicResponseDTO.class)).toList();
        return listaTopicResponseDTOS;
    }


    public List<TopicResponseDTO> listarAscendenteTopic() {
        List<TopicoEntity> topicoEntities = topicoRepository.findTop10ByOrderByFechaCreacionAsc();
        List<TopicResponseDTO> topicResponseDTOS = topicoEntities.stream().map(t -> modelMapper.map(t, TopicResponseDTO.class)).toList();
        return topicResponseDTOS;
    }

    public Page<TopicResponseDTO> listarCursoAnioTopic(Pageable pageable, String curso, int anio){
        LocalDateTime inicioDelAnio = LocalDateTime.of(anio, Month.JANUARY, 1, 0, 0, 0);
        LocalDateTime finDelAnio = LocalDateTime.of(anio, Month.DECEMBER, 31, 23, 59, 59);
        Page<TopicoEntity> topicos = topicoRepository.encontrarPorCursoYAnio(pageable, curso, inicioDelAnio, finDelAnio);
        return topicos.map(t -> modelMapper.map(t, TopicResponseDTO.class));
    }
}
