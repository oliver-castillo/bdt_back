package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ICurrencyResponse {

    int getId();

    String getCurrency();

    String getAbbreviation();

}
