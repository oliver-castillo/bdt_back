package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface IListUserTalentResponse {

  @Value("#{target.USER_ID}")
  Long getUserId();

  @Value("#{target.LIST_ID}")
  Long getListId();

  @Value("#{target.LIST_NAME}")
  String getListName();

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

}
