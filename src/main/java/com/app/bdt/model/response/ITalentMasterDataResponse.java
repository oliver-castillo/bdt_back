package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ITalentMasterDataResponse {

  @Value("#{target.TALENT_ID}")
  Long getTalentId();

  @Value("#{target.COUNTRY_ID}")
  Integer getCountryId();

  @Value("#{target.CITY_ID}")
  Integer getCityId();

  @Value("#{target.CURRENCY_ID}")
  Integer getCurrencyId();

  @Value("#{target.PROFILE_ID}")
  Integer getProfileId();

  String getCountry();

  String getCity();

  String getCurrency();

  String getProfile();
}
