package com.app.bdt.repository;

import com.app.bdt.model.entity.*;
import com.app.bdt.model.response.ITalentByLanguageAndLevel;
import com.app.bdt.model.response.ITalentByTechnicalSkills;
import com.app.bdt.model.response.ITalentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

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

  @Query(value = "CALL SP_GET_TALENT_BY_ID", nativeQuery = true)
  Talent findTalentById(@Param("talentId") Long talentId);

  @Query(value = "CALL SP_GET_TALENTS_BY_TECHNICAL_SKILLS(:technicalSkills)", nativeQuery = true)
  List<ITalentByTechnicalSkills> findTalentsByTechnicalSkills(@Param("technicalSkills") String technicalSkills);

  @Query(value = "CALL SP_GET_TALENTS_BY_LANGUAGE_AND_LEVEL(:languageId, :levelId)", nativeQuery = true)
  List<ITalentByLanguageAndLevel> findTalentsByLanguageAndLevel(@Param("languageId") int languageId, @Param("levelId") int levelId);

  @Query(value = "CALL SP_FILTER_TALENTS_IDS_BY_TECHNICAL_SKILLS_LANGUAGE_AND_LEVEL(" +
          ":languageId, " +
          ":levelId, " +
          ":technicalSkills)", nativeQuery = true)
  List<ITalentResponse> findTalentsIdsByTechnicalSkillsLanguageIdAndLevelId(
          @Param("languageId") Integer languageId,
          @Param("levelId") Integer levelId,
          @Param("technicalSkills") String technicalSkills);

}
