package com.app.bdt.service;

import com.app.bdt.model.response.CityResponse;
import com.app.bdt.model.response.CountryResponse;

import java.util.List;

public interface ICountriesAndCitiesService {

  List<CountryResponse> getCountries();

  CountryResponse getCountryById(int id);

  List<CityResponse> getCities();

  CityResponse getCityById(int id);

  List<CityResponse> getCitiesByCountry(int id);

}
