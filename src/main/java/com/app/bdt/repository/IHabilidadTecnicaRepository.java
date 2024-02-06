package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.HabilidadTecnica;

@Repository
public interface IHabilidadTecnicaRepository extends JpaRepository<HabilidadTecnica, Long> {

}
