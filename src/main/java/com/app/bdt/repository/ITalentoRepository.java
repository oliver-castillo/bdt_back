package com.app.bdt.repository;

import com.app.bdt.model.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITalentoRepository extends JpaRepository<Talent, Long> {

}
