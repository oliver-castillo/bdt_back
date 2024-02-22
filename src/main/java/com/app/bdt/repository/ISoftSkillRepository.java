package com.app.bdt.repository;

import com.app.bdt.model.entity.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ISoftSkillRepository extends JpaRepository<SoftSkill, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_SOFT_SKILL(:talentId, :#{#softSkill.skill})", nativeQuery = true)
  void createSoftSkill(@Param("talentId") Long talentId, @Param("softSkill") SoftSkill softSkill);

}
