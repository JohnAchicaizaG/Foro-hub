package com.principal.forohub.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.principal.forohub.entities.StatusEntity;
import com.principal.forohub.entities.TopicoEntity;

public interface TopicoRepository extends JpaRepository<TopicoEntity,Long> {

    @Query("select t from Topico t where t.status = :status")
    List<TopicoEntity> encontrarTopicosActivos(StatusEntity statusEntity);


    List<TopicoEntity> findTop10ByOrderByFechaCreacionAsc();

    @Query("SELECT t FROM Topico t JOIN t.curso c where c.nombre = :curso and t.fechaCreacion between :inicioDelAnio and :finDelAnio")
    Page<TopicoEntity> encontrarPorCursoYAnio(Pageable pageable, String curso, LocalDateTime inicioDelAnio, LocalDateTime finDelAnio);

}
