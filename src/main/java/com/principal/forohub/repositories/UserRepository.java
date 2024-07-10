package com.principal.forohub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.forohub.entities.UsuarioEntity;

public interface UserRepository extends JpaRepository<UsuarioEntity, Long>{

    Optional<UsuarioEntity> findByNombre(String username);

}
