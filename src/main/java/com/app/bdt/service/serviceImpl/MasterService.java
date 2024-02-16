package com.app.bdt.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.app.bdt.service.IStoredProceduresService;
import org.springframework.stereotype.Service;

import com.app.bdt.model.response.LanguageResponse;
import com.app.bdt.model.response.CurrencyResponse;
import com.app.bdt.model.response.LevelResponse;
import com.app.bdt.model.response.ProfileResponse;
import com.app.bdt.model.response.RoleResponse;
import com.app.bdt.repository.IMasterRepository;
import com.app.bdt.service.IMasterService;

@Service
@Transactional
public class MasterService implements IMasterService {

  Logger log = Logger.getLogger(this.getClass().getName());

  private final IMasterRepository masterRepository;
  private final EntityManager entityManager;

  private final IStoredProceduresService storedProceduresService;

  public MasterService(IMasterRepository masterRepository, EntityManager entityManager, IStoredProceduresService storedProceduresService) {
    this.masterRepository = masterRepository;
    this.entityManager = entityManager;
    this.storedProceduresService = storedProceduresService;
  }

  @Override
  public List<RoleResponse> getRoles() {
    try {
      List<Object[]> objects = storedProceduresService.getObjects("SP_OBTENER_ROLES");
      List<RoleResponse> roles = new ArrayList<>();
      for (Object[] object : objects) {
        int id = Integer.parseInt(object[0].toString());
        String role = (String) object[1];
        String abbreviation = (String) object[2];
        roles.add(new RoleResponse(id, role, abbreviation));
      }
      return roles;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<CurrencyResponse> getCurrencies() {
    try {
      List<Object[]> objects = storedProceduresService.getObjects("SP_OBTENER_MONEDAS");
      List<CurrencyResponse> currencies = new ArrayList<>();
      for (Object[] object : objects) {
        int id = Integer.parseInt(object[0].toString());
        String currency = (String) object[1];
        String abbreviation = (String) object[2];
        currencies.add(new CurrencyResponse(id, currency, abbreviation));
      }
      return currencies;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<ProfileResponse> getProfiles() {
    try {
      List<Object[]> objects = storedProceduresService.getObjects("SP_OBTENER_PERFILES");
      List<ProfileResponse> profiles = new ArrayList<>();
      for (Object[] object : objects) {
        int id = Integer.parseInt(object[0].toString());
        String profile = (String) object[1];
        String abbreviation = (String) object[2];
        profiles.add(new ProfileResponse(id, profile, abbreviation));
      }
      return profiles;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<LanguageResponse> getLanguages() {
    try {
      List<Object[]> objects = storedProceduresService.getObjects("SP_OBTENER_IDIOMAS");
      List<LanguageResponse> languages = new ArrayList<>();
      for (Object[] object : objects) {
        int id = Integer.parseInt(object[0].toString());
        String language = (String) object[1];
        String abbreviation = (String) object[2];
        languages.add(new LanguageResponse(id, language, abbreviation));
      }
      return languages;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

  @Override
  public List<LevelResponse> getLevels() {
    try {
      List<Object[]> objects = storedProceduresService.getObjects("SP_OBTENER_NIVELES");
      List<LevelResponse> levels = new ArrayList<>();
      for (Object[] object : objects) {
        int id = Integer.parseInt(object[0].toString());
        String level = (String) object[1];
        levels.add(new LevelResponse(id, level));
      }
      return levels;
    } catch (Exception e) {
      log.warning(e.getMessage());
      return null;
    }
  }

}
