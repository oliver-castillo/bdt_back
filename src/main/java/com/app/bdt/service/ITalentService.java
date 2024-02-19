package com.app.bdt.service;

import java.util.List;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;

public interface ITalentService {
    List<TalentDto> getTalents();

    TalentDto createTalent(TalentDto talent);
}
