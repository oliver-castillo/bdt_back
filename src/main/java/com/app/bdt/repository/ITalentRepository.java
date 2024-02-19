package com.app.bdt.repository;

import com.app.bdt.model.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITalentRepository extends JpaRepository<Talent, Long> {

}
