package com.app.bdt.service;

import java.util.List;

import com.app.bdt.model.response.LanguageResponse;
import com.app.bdt.model.response.CurrencyResponse;
import com.app.bdt.model.response.LevelResponse;
import com.app.bdt.model.response.ProfileResponse;
import com.app.bdt.model.response.RoleResponse;

public interface IMasterService {

    List<RoleResponse> getRoles();

    List<CurrencyResponse> getCurrencies();

    List<ProfileResponse> getProfiles();

    List<LanguageResponse> getLanguages();

    List<LevelResponse> getLevels();

}
