package com.app.bdt.service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.SoftSkillRequest;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.request.TechnicalSkillRequest;
import com.app.bdt.model.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITalentService {
  List<TalentDto> getTalents();

  Optional<TalentDto> getTalentDtoById(Long talentId);

  TalentDto createTalent(TalentRequest talentRequest);

  TalentDto updateTalent(Long talentId, TalentRequest talentRequest);

  Response updateSalaryBandOfTalent(Long talentId, TalentRequest talentRequest);

  Response addTechnicalSkill(Long talentId, TechnicalSkillRequest technicalSkillRequest);

  Response addSoftSkill(Long talentId, SoftSkillRequest softSkillRequest);

  List<Map<String, Object>> getTalentsByTechnicalSkillsLanguageAndLevel(Map<String, Object> params);

}
