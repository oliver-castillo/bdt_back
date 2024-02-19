package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ILanguageResponse {

    int getId();

    String getLanguage();

    String getAbbreviation();

}
