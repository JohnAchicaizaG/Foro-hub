package com.principal.forohub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.profile.ProfileRequestDTO;
import com.principal.forohub.dto.profile.ProfileResponseDTO;
import com.principal.forohub.entities.PerfilEntity;
import com.principal.forohub.repositories.ProfileRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PerfilService implements IServices<ProfileRequestDTO, ProfileResponseDTO>{

    @Autowired
    private ProfileRepository profileRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ProfileResponseDTO actualizarTopico(Long id, ProfileRequestDTO nuevosDatos) {
        PerfilEntity perfilEntity = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        perfilEntity.setNombre(nuevosDatos.getNombre());
        return modelMapper.map(profileRepository.save(perfilEntity), ProfileResponseDTO.class);
    }

    public void eliminarCategoriaTopic(Long id) {
        PerfilEntity perfilEntity = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        profileRepository.delete(perfilEntity);
    }

    public ProfileResponseDTO encontrarCategoriaTopic(Long id) {
        PerfilEntity perfilEntityEncontrado = profileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(perfilEntityEncontrado, ProfileResponseDTO.class);
    }

    public ProfileResponseDTO guardarCategoriaTopic(ProfileRequestDTO nuevoRegistro) {
        PerfilEntity perfilEntityNuevo = modelMapper.map(nuevoRegistro, PerfilEntity.class);
        return modelMapper.map(profileRepository.save(perfilEntityNuevo), ProfileResponseDTO.class);
    }

    public List<ProfileResponseDTO> listarTopic() {
        List<PerfilEntity> perfiles = profileRepository.findAll();
        List<ProfileResponseDTO> perfilesResponseDTOs = perfiles.stream().map(p -> modelMapper.map(p, ProfileResponseDTO.class)).toList();
        return perfilesResponseDTOs;
    }
}
