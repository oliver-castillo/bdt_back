package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ICityResponse {

  int getId();

  String getCity();

  @Value("#{target.COUNTRY_ID}")
  int getCountryId();

}
