package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.model.response.*;
import com.app.bdt.repository.IMasterRepository;
import com.app.bdt.service.IMasterService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MasterService implements IMasterService {

  private final IMasterRepository masterRepository;

  public MasterService(IMasterRepository masterRepository, EntityManager entityManager) {
    this.masterRepository = masterRepository;
  }

  @Override
  public List<IRoleResponse> getRoles() {
    try {
      return masterRepository.findAllRoles();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }

  }

  @Override
  public List<ICurrencyResponse> getCurrencies() {
    try {
      return masterRepository.findAllCurrencies();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<IProfileResponse> getProfiles() {
    try {
      return masterRepository.findAllProfiles();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ILanguageResponse> getLanguages() {
    try {
      return masterRepository.findAllLanguages();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ILevelResponse> getLevels() {
    try {
      return masterRepository.findAllLevels();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ICountryResponse> getCountries() {
    try {
      return masterRepository.findAllCountries();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ICityResponse> getCities() {
    try {
      return masterRepository.findAllCities();
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ICityResponse> getCitiesByCountry(int countryId) {
    try {
      return getCities().stream()
              .filter(city -> city.getCountryId() == countryId)
              .collect(Collectors.toList());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

}
