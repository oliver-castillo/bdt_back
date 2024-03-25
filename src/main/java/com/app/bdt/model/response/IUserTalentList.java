package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface IUserTalentList {

  @Value("#{target.ID}")
  Long getId();

  @Value("#{target.LIST_ID}")
  Long getListId();

  @Value("#{target.TALENT_ID}")
  Long getTalentId();
}
