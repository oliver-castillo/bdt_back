package com.app.bdt.service;

import java.util.List;

import com.app.bdt.model.response.ICityResponse;
import com.app.bdt.model.response.ICountryResponse;
import com.app.bdt.model.response.ICurrencyResponse;
import com.app.bdt.model.response.ILanguageResponse;
import com.app.bdt.model.response.ILevelResponse;
import com.app.bdt.model.response.IProfileResponse;
import com.app.bdt.model.response.IRoleResponse;

public interface IMasterService {

  List<IRoleResponse> getRoles();

  List<ICurrencyResponse> getCurrencies();

  List<IProfileResponse> getProfiles();

  List<ILanguageResponse> getLanguages();

  List<ILevelResponse> getLevels();

  List<ICountryResponse> getCountries();

  List<ICityResponse> getCities();

  List<ICityResponse> getCitiesByCountry(int countryId);

}
