package com.app.bdt.service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.*;
import com.app.bdt.model.response.Response;
import com.app.bdt.model.response.TalentCardResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ITalentService {
  List<TalentDto> getAllTalents();

  Optional<TalentDto> getTalentDtoById(Long talentId);

  List<TalentCardResponse> getAllTalentsWithBasicData();

  TalentDto create(TalentRequest talentRequest);

  TalentDto updateTalent(Long talentId, TalentRequest talentRequest);

  Response updateSocials(Long talentId, SocialRequest socialRequest);

  Response updateSalaryBand(Long talentId, SalaryBandRequest salaryBandRequest);

  Response updateDescription(Long talentId, String description);

  Response addFile(Long talentId, FileRequest file);

  Response addTechnicalSkill(Long talentId, TechnicalSkillRequest technicalSkillRequest);

  Response addSoftSkill(Long talentId, SoftSkillRequest softSkillRequest);

  Response addWorkExperience(Long talentId, WorkExperienceRequest workExperienceRequest);

  Response addEducationalExperience(Long talentId, EducationalExperienceRequest educationalExperienceRequest);

  Response addLanguage(Long talentId, LanguageRequest languageRequest);

  Response addFeedback(FeedbackRequest feedbackRequest);

  Response updateWorkExperience(Long talentId, Long workExperienceId, WorkExperienceRequest workExperienceRequest);

  Response updateEducationalExperience(Long talentId, Long educationalExperienceId, EducationalExperienceRequest educationalExperienceRequest);

  Response updateLanguage(Long talentId, Long id, LanguageRequest languageRequest);

  Response updateImage(Long talentId, ImageRequest imageRequest);

  Response updateCV(Long talentId, Long fileId, FileRequest fileRequest);

  List<TalentCardResponse> filterByParams(FilterParamsRequest filterParamsRequest);

  List<TalentCardResponse> getByIds(Set<Long> ids);

}
