package com.principal.forohub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.principal.forohub.entities.PerfilEntity;

public interface ProfileRepository extends JpaRepository<PerfilEntity, Long> {

}
