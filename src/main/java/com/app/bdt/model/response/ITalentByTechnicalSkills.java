package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ITalentByTechnicalSkills {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  @Value("#{target.TECHNICAL_SKILL}")
  String getTechnicalSkill();

}
