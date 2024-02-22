package com.app.bdt.repository;

import com.app.bdt.model.entity.TechnicalSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ITechnicalSkillRepository extends JpaRepository<TechnicalSkill, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_TECHNICAL_SKILL(:talentId, :#{#technicalSkill.skill}, :#{#technicalSkill.years})", nativeQuery = true)
  void createTechnicalSkill(@Param("talentId") Long talentId, @Param("technicalSkill") TechnicalSkill technicalSkill);

}
