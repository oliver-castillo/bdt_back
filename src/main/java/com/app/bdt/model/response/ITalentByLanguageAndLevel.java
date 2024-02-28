package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ITalentByLanguageAndLevel {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getLanguage();

  String getLevel();

}
