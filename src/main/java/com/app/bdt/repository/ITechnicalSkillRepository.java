package com.app.bdt.repository;

import com.app.bdt.model.entity.TechnicalSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITechnicalSkillRepository extends JpaRepository<TechnicalSkill, Long> {

}
