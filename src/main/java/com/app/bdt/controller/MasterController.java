package com.app.bdt.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.bdt.service.ICountriesAndCitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.bdt.service.IMasterService;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:4200")
public class MasterController {

  private final IMasterService masterService;
  private final ICountriesAndCitiesService countriesAndCitiesService;

  public MasterController(IMasterService masterService, ICountriesAndCitiesService countriesAndCitiesService) {
    this.masterService = masterService;
    this.countriesAndCitiesService = countriesAndCitiesService;
  }

  @GetMapping("/{description}")
  public ResponseEntity<Object> showData(@PathVariable(name = "description") String description) {
    List<?> result = new ArrayList<>();
    switch (description) {
      case "countries":
        result = countriesAndCitiesService.getCountries();
        break;
      case "cities":
        result = countriesAndCitiesService.getCities();
        break;
      case "roles":
        result = masterService.getRoles();
        break;
      case "currencies":
        result = masterService.getCurrencies();
        break;
      case "profiles":
        result = masterService.getProfiles();
        break;
      case "languages":
        result = masterService.getLanguages();
        break;
      case "levels":
        result = masterService.getLevels();
        break;
      default:
        break;
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping("/country/{id}")
  public ResponseEntity<Object> getCountryById(@PathVariable int id) {
    return new ResponseEntity<>(countriesAndCitiesService.getCountryById(id), HttpStatus.OK);
  }

  @GetMapping("/city/{id}")
  public ResponseEntity<Object> getCityById(@PathVariable int id) {
    return new ResponseEntity<>(countriesAndCitiesService.getCityById(id), HttpStatus.OK);
  }

  @GetMapping("/country/{id}/cities")
  public ResponseEntity<Object> getCitiesByCountry(@PathVariable int id) {
    return new ResponseEntity<>(countriesAndCitiesService.getCitiesByCountry(id), HttpStatus.OK);
  }

}
