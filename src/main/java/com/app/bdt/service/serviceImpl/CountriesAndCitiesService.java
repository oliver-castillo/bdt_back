package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.response.CityResponse;
import com.app.bdt.model.response.CountryResponse;
import com.app.bdt.service.ICountriesAndCitiesService;
import com.app.bdt.service.IStoredProceduresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CountriesAndCitiesService implements ICountriesAndCitiesService {

  private final IStoredProceduresService storedProcedures;

  @Autowired
  public CountriesAndCitiesService(IStoredProceduresService storedProcedures) {
    this.storedProcedures = storedProcedures;
  }

  @Override
  public List<CountryResponse> getCountries() {
    List<Object[]> objects = storedProcedures.getObjects("SP_OBTENER_PAISES");
    if (objects == null || objects.isEmpty()) {
      return Collections.emptyList();
    }
    List<CountryResponse> countries = new ArrayList<>();
    for (Object[] object : objects) {
      int id = ((Number) object[0]).intValue();
      String country = (String) object[1];
      String abbreviation = (String) object[2];
      countries.add(new CountryResponse(id, country, abbreviation));
    }
    return countries;
  }

  @Override
  public CountryResponse getCountryById(int id) {
    for (CountryResponse country : getCountries()) {
      if (country.getId() == id) {
        return country;
      }
    }
    throw new NotFoundException("No se encontró el registro.");
  }

  @Override
  public List<CityResponse> getCities() {
    List<Object[]> objects = storedProcedures.getObjects("SP_OBTENER_CIUDADES");
    List<CityResponse> cities = new ArrayList<>();
    for (Object[] object : objects) {
      int id = Integer.parseInt(object[0].toString());
      String city = (String) object[1];
      int countryId = Integer.parseInt(object[2].toString());
      cities.add(new CityResponse(id, city, countryId));
    }
    return cities;
  }

  @Override
  public CityResponse getCityById(int id) {
    for (CityResponse city : getCities()) {
      if (city.getId() == id) {
        return city;
      }
    }
    throw new NotFoundException("No se encontró el registro");
  }

  @Override
  public List<CityResponse> getCitiesByCountry(int id) {
    if (getCountryById(id) != null) {
      List<CityResponse> citiesByCountry = new ArrayList<>();
      for (CityResponse city : getCities()) {
        if (city.getCountryId() == id) {
          citiesByCountry.add(city);
        }
      }
      return citiesByCountry;
    }
    throw new NotFoundException("No se encontró el registro");
  }

}
