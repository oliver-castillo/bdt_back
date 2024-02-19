package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ICountryResponse {

    int getId();

    String getCountry();

    String getAbbreviation();

}