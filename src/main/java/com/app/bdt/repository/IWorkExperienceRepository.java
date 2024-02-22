package com.app.bdt.repository;

import com.app.bdt.model.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IWorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_WORK_EXPERIENCE(:talentId, :#{#workExperience.company}, :#{#workExperience.position}, :#{#workExperience.startDate}, :#{#workExperience.endDate})", nativeQuery = true)
  void createWorkExperience(@Param("talentId") Long talentId, @Param("workExperience") WorkExperience workExperience);

}
