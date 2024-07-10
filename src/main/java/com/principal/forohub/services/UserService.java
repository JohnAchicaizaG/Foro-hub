package com.principal.forohub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.principal.forohub.dto.users.UsersRequestDTO;
import com.principal.forohub.dto.users.UsersResponseDTO;
import com.principal.forohub.entities.PerfilEntity;
import com.principal.forohub.entities.UsuarioEntity;
import com.principal.forohub.repositories.ProfileRepository;
import com.principal.forohub.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class UserService implements IServices<UsersRequestDTO, UsersResponseDTO> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public UsersResponseDTO actualizarTopico(Long id, UsersRequestDTO nuevosDatos) {
        UsuarioEntity usuarioEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        PerfilEntity perfilEntity = profileRepository.findById(nuevosDatos.getPerfilEntity().getId()).orElseThrow(() -> new EntityNotFoundException());
        usuarioEntity.setContrasena(passwordEncoder.encode(nuevosDatos.getContrasena()));
        usuarioEntity.setCorreoElectronico(nuevosDatos.getCorreoElectronico());
        usuarioEntity.setNombre(nuevosDatos.getNombre());
        usuarioEntity.setPerfilEntity(perfilEntity);
        return modelMapper.map(userRepository.save(usuarioEntity), UsersResponseDTO.class);
    }

    public void eliminarCategoriaTopic(Long id) {
        UsuarioEntity usuarioEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        userRepository.delete(usuarioEntity);
    }

    public UsersResponseDTO encontrarCategoriaTopic(Long id) {
        UsuarioEntity usuarioEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return modelMapper.map(usuarioEntity, UsersResponseDTO.class);
    }

    public UsersResponseDTO guardarCategoriaTopic(UsersRequestDTO nuevoRegistro) {
        UsuarioEntity usuarioEntity = modelMapper.map(nuevoRegistro, UsuarioEntity.class);
        usuarioEntity.setContrasena(passwordEncoder.encode(nuevoRegistro.getContrasena()));
        return modelMapper.map(userRepository.save(usuarioEntity), UsersResponseDTO.class);
    }

    public List<UsersResponseDTO> listarTopic() {
        List<UsuarioEntity> usuarioEntities = userRepository.findAll();
        List<UsersResponseDTO> usersResponseDTOS = usuarioEntities.stream().map(u -> modelMapper.map(u, UsersResponseDTO.class)).toList();
        return usersResponseDTOS;
    }


    

}
