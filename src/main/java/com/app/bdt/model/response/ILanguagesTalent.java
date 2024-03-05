package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ILanguagesTalent {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getLanguage();

  @Value("#{target.LANGUAGE_ID}")
  Integer getLanguageId();

  String getLevel();

  @Value("#{target.NUMBER_OF_STARS}")
  String getNumberOfStars();
}
