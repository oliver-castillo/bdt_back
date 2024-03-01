package com.app.bdt.service;

import com.app.bdt.model.response.*;

import java.util.List;

public interface IMasterService {

  List<IRoleResponse> getRoles();

  List<ICurrencyResponse> getCurrencies();

  List<IProfileResponse> getProfiles();

  List<ILanguageResponse> getLanguages();

  List<ILevelResponse> getLevels();

  List<ICountryResponse> getCountries();

  List<ICityResponse> getCities();

  List<ICityResponse> getCitiesByCountry(int countryId);

  List<String> getAllTechnicalSkills();

}
