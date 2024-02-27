package com.app.bdt.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.EducationalExperience;
import com.app.bdt.model.entity.File;
import com.app.bdt.model.entity.Language;
import com.app.bdt.model.entity.SoftSkill;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.entity.TechnicalSkill;
import com.app.bdt.model.entity.WorkExperience;

@Repository
public interface ITalentRepository extends JpaRepository<Talent, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_TALENT(:#{#talent.name}, :#{#talent.paternalSurname}, :#{#talent.maternalSurname}, :#{#talent.image}, :#{#talent.description}, :#{#talent.initialAmount}, :#{#talent.finalAmount}, :#{#talent.cellPhoneNumber}, :#{#talent.linkedinLink}, :#{#talent.githubLink})", nativeQuery = true)
  void createTalent(@Param("talent") Talent talent);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_TECHNICAL_SKILL(:talentId, :#{#technicalSkill.skill}, :#{#technicalSkill.years})", nativeQuery = true)
  void addTechnicalSkill(@Param("talentId") Long talentId, @Param("technicalSkill") TechnicalSkill technicalSkill);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_SOFT_SKILL(:talentId, :#{#softSkill.skill})", nativeQuery = true)
  void addSoftSkill(@Param("talentId") Long talentId, @Param("softSkill") SoftSkill softSkill);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_WORK_EXPERIENCE(:talentId, :#{#workExperience.company}, :#{#workExperience.position}, :#{#workExperience.startDate}, :#{#workExperience.endDate})", nativeQuery = true)
  void addWorkExperience(@Param("talentId") Long talentId, @Param("workExperience") WorkExperience workExperience);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_EDUCATIONAL_EXPERIENCE(:talentId, :#{#educationalExperience.educationalInstitute}, :#{#educationalExperience.career}, :#{#educationalExperience.degree}, :#{#educationalExperience.startDate}, :#{#educationalExperience.endDate})", nativeQuery = true)
  void addEducationalExperience(@Param("talentId") Long talentId,
      @Param("educationalExperience") EducationalExperience educationalExperience);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_TALENT_LANGUAGE(:talentId, :#{#language.languageId}, :#{#language.levelId}, :#{#language.numberOfStars})", nativeQuery = true)
  void addLanguage(@Param("talentId") Long talentId, @Param("language") Language language);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_FILE_TALENT(:talentId, :#{#file.fileName}, :#{#file.fileType}, :#{#file.fileInBytes})", nativeQuery = true)
  void addFile(@Param("talentId") Long talentId, @Param("file") File file);

  @Query(value = "CALL SP_GET_LAST_INSERTED_TALENT", nativeQuery = true)
  Talent getLastInsertedTalent();

  @Query(value = "CALL SP_GET_TALENTS", nativeQuery = true)
  List<Talent> findAllTalents();

}
