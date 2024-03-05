package com.app.bdt.service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.*;
import com.app.bdt.model.response.Response;
import com.app.bdt.model.response.TalentCardResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITalentService {
  List<TalentDto> getAllTalents();

  Optional<TalentDto> getTalentDtoById(Long talentId);

  List<TalentCardResponse> getAllTalentsWithBasicData();

  TalentDto create(TalentRequest talentRequest);

  TalentDto updateTalent(Long talentId, TalentRequest talentRequest);

  Response updateSalaryBand(Long talentId, TalentRequest talentRequest);

  Response updateDescription(Long talentId, String description);

  Response addTechnicalSkill(Long talentId, TechnicalSkillRequest technicalSkillRequest);

  Response addSoftSkill(Long talentId, SoftSkillRequest softSkillRequest);

  Response addWorkExperience(Long talentId, WorkExperienceRequest workExperienceRequest);

  Response addEducationalExperience(Long talentId, EducationalExperienceRequest educationalExperienceRequest);

  Response addLanguage(Long talentId, LanguageRequest languageRequest);

  Response updateWorkExperience(Long talentId, Long workExperienceId, WorkExperienceRequest workExperienceRequest);

  Response updateEducationalExperience(Long talentId, Long educationalExperienceId, EducationalExperienceRequest educationalExperienceRequest);

  List<TalentCardResponse> getByTechnicalSkillsLanguageAndLevel(Map<String, Object> params);

}
