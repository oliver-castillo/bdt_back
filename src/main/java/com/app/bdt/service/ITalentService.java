package com.app.bdt.service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.response.ITalentByLanguageAndLevel;

import java.util.List;
import java.util.Map;

public interface ITalentService {
  List<TalentDto> getTalents();

  TalentDto createTalent(TalentRequest talentRequest);

  TalentDto updateTalent(Long talentId, TalentRequest talentRequest);

  List<Map<String, Object>> getTalentsByTechnicalSkills(List<String> technicalSkills);

  List<ITalentByLanguageAndLevel> getTalentsByLanguageAndLevel(int languageId, int levelId);

}
