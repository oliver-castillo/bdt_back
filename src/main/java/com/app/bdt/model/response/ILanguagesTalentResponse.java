package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ILanguagesTalentResponse {

  Long getId();

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getLanguage();

  @Value("#{target.LANGUAGE_ID}")
  Integer getLanguageId();

  @Value("#{target.LEVEL_ID}")
  Integer getLevelId();

  String getLevel();

  @Value("#{target.NUMBER_OF_STARS}")
  String getNumberOfStars();
}
