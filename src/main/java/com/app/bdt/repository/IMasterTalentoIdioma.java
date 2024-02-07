package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.MasterTalentoIdioma;

@Repository
public interface IMasterTalentoIdioma extends JpaRepository<MasterTalentoIdioma, Long> {

}
