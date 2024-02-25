package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ITalentMasterDataResponse {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  String getCountry();

  String getCity();

  String getCurrency();

  String getProfile();
}
