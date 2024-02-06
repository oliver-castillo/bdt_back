package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.MasterTalento;

@Repository
public interface IMasterTalentoRepository extends JpaRepository<MasterTalento, Long> {

}
