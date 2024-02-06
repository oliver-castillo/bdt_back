package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.ExperienciaLaboral;

@Repository
public interface IExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Long> {

}
