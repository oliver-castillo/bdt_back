package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ILanguagesTalent {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getLanguage();

  String getLevel();

  @Value("#{target.NUMBER_OF_STARS}")
  String getNumberOfStars();
}
