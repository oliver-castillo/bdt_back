package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface ITalentResponse {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getLanguage();

  String getLevel();

  @Value("#{target.TECHNICAL_SKILL}")
  List<String> getTechnicalSkills();

}
