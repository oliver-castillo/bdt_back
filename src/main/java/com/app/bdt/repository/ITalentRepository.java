package com.app.bdt.repository;

import com.app.bdt.model.entity.*;
import com.app.bdt.model.request.EducationalExperienceRequest;
import com.app.bdt.model.request.FeedbackRequest;
import com.app.bdt.model.request.WorkExperienceRequest;
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
import java.util.Optional;

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
  @Query(value = "CALL SP_INSERT_WORK_EXPERIENCE(:talentId, :#{#workExperience.company}, :#{#workExperience.position}, :#{#workExperience.startDate}, :#{#workExperience.endDate}, :#{#workExperience.current})", nativeQuery = true)
  void addWorkExperience(@Param("talentId") Long talentId, @Param("workExperience") WorkExperience workExperience);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_EDUCATIONAL_EXPERIENCE(:talentId, :#{#educationalExperience.educationalInstitute}, :#{#educationalExperience.career}, :#{#educationalExperience.degree}, :#{#educationalExperience.startDate}, :#{#educationalExperience.endDate}, :#{#educationalExperience.current})", nativeQuery = true)
  void addEducationalExperience(@Param("talentId") Long talentId,
                                @Param("educationalExperience") EducationalExperience educationalExperience);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_FEEDBACK(:#{#feedback.talentId}, :#{#feedback.starsRating}, :#{#feedback.description}, :#{#feedback.userId})", nativeQuery = true)
  void addFeedback(@Param("feedback") FeedbackRequest feedbackRequest);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_UPDATE_WORK_EXPERIENCE(" +
          ":talentId, :workExpId, :#{#workExp.company}," +
          ":#{#workExp.position}, :#{#workExp.startDate}, :#{#workExp.endDate}, :#{#workExp.current})", nativeQuery = true)
  void updateWorkExperience(
          @Param("talentId") Long talentId,
          @Param("workExpId") Long workExperienceId,
          @Param("workExp") WorkExperienceRequest workExperienceRequest);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_UPDATE_EDUCATIONAL_EXPERIENCE(" +
          ":talentId, :eduExperienceId, :#{#eduExp.educationalInstitute}," +
          ":#{#eduExp.career}, :#{#eduExp.degree}, :#{#eduExp.startDate}, :#{#eduExp.endDate}, :#{#eduExp.current})", nativeQuery = true)
  void updateEducationalExperience(
          @Param("talentId") Long talentId,
          @Param("eduExperienceId") Long eduExperienceId,
          @Param("eduExp") EducationalExperienceRequest educationalExperienceRequest);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_UPDATE_LANGUAGE_BY_TALENT(" +
          ":talentId, " +
          ":id, " +
          ":#{#language.languageId}, " +
          ":#{#language.levelId}, " +
          ":#{#language.numberOfStars})", nativeQuery = true)
  void updateLanguage(@Param("talentId") Long talentId, Long id, @Param("language") Language language);

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

  @Query(value = "CALL SP_GET_TALENT_BY_ID(:talentId)", nativeQuery = true)
  Optional<Talent> findTalentById(@Param("talentId") Long talentId);

  @Query(value = "CALL SP_GET_TALENTS_BY_TECHNICAL_SKILLS(:technicalSkills)", nativeQuery = true)
  List<ITalentByTechnicalSkills> findTalentsByTechnicalSkills(@Param("technicalSkills") String technicalSkills);

  @Query(value = "CALL SP_GET_TALENTS_BY_LANGUAGE_AND_LEVEL(:languageId, :levelId)", nativeQuery = true)
  List<ITalentByLanguageAndLevel> findTalentsByLanguageAndLevel(@Param("languageId") int languageId, @Param("levelId") int levelId);

  @Query(value = "CALL SP_FILTER_TALENTS(" +
          ":languageId, " +
          ":levelId, " +
          ":technicalSkills)", nativeQuery = true)
  List<ITalentResponse> findTalentsIdsByTechnicalSkillsLanguageIdAndLevelId(
          @Param("languageId") Integer languageId,
          @Param("levelId") Integer levelId,
          @Param("technicalSkills") String technicalSkills);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_UPDATE_TALENT(:talentId, :#{#talent.name}, :#{#talent.paternalSurname}, :#{#talent.maternalSurname}, :#{#talent.image}, :#{#talent.description}, :#{#talent.initialAmount}, :#{#talent.finalAmount}, :#{#talent.cellPhoneNumber}, :#{#talent.linkedinLink}, :#{#talent.githubLink})", nativeQuery = true)
  void updateTalent(@Param("talentId") Long talentId, @Param("talent") Talent talent);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_UPDATE_CURRENCY_TALENT(:talentId, :currencyId)", nativeQuery = true)
  void updateCurrency(@Param("talentId") Long talentId, @Param("currencyId") Integer currencyId);


  @Modifying
  @Transactional
  @Query(value = "CALL SP_UPDATE_FILE(:#{#file.id}, :#{#file.fileName}, :#{#file.fileType}, :#{#file.fileInBytes}, :talentId)", nativeQuery = true)
  void updateFile(@Param("talentId") Long talentId, @Param("file") File file);

}
