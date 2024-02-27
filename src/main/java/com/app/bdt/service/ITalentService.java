package com.app.bdt.service;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;

import java.util.List;

public interface ITalentService {
  List<TalentDto> getTalents();

  TalentDto createTalent(TalentRequest talentRequest);

  TalentDto updateTalent(Long talentId, TalentRequest talentRequest);

}
