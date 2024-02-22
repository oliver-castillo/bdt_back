package com.app.bdt.repository;

import com.app.bdt.model.entity.EducationalExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface IEducationalExperienceRepository extends JpaRepository<EducationalExperience, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_EDUCATIONAL_EXPERIENCE(:talentId, :#{#educationalExperience.educationalInstitute}, :#{#educationalExperience.career}, :#{#educationalExperience.degree}, :#{#educationalExperience.startDate}, :#{#educationalExperience.endDate})", nativeQuery = true)
  void createEducationalExperience(@Param("talentId") Long talentId, @Param("educationalExperience") EducationalExperience educationalExperience);

}
