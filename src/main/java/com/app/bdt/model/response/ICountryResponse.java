package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface ICountryResponse {

  int getId();

  String getCountry();

  String getAbbreviation();

  @Value("#{target.CALL_PREFIX}")
  String getCallPrefix();

}