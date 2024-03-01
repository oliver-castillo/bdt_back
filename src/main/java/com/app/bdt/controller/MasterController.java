package com.app.bdt.controller;

import com.app.bdt.service.IMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MasterController {

  private final IMasterService masterService;

  @GetMapping("/{description}")
  public ResponseEntity<Object> showData(@PathVariable(name = "description") String description) {
    List<?> result = new ArrayList<>();
    switch (description) {
      case "countries":
        result = masterService.getCountries();
        break;
      case "cities":
        result = masterService.getCities();
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

  @GetMapping("/country/{id}/cities")
  public ResponseEntity<Object> getCitiesByCountry(@PathVariable int id) {
    return new ResponseEntity<>(masterService.getCitiesByCountry(id), HttpStatus.OK);
  }

  @GetMapping("/all_technical_skills")
  public ResponseEntity<Object> getAllTechnicalSkills() {
    return new ResponseEntity<>(masterService.getAllTechnicalSkills(), HttpStatus.OK);
  }

}
