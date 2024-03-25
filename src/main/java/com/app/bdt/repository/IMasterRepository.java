package com.app.bdt.repository;

import com.app.bdt.model.entity.Master;
import com.app.bdt.model.response.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMasterRepository extends JpaRepository<Master, Long> {

  @Query(value = "CALL SP_GET_ROLES", nativeQuery = true)
  List<IRoleResponse> findAllRoles();

  @Query(value = "CALL SP_GET_CURRENCIES", nativeQuery = true)
  List<ICurrencyResponse> findAllCurrencies();

  @Query(value = "CALL SP_GET_PROFILES", nativeQuery = true)
  List<IProfileResponse> findAllProfiles();

  @Query(value = "CALL SP_GET_LANGUAGES", nativeQuery = true)
  List<ILanguageResponse> findAllLanguages();

  @Query(value = "CALL SP_GET_LEVELS", nativeQuery = true)
  List<ILevelResponse> findAllLevels();

  @Query(value = "CALL SP_GET_COUNTRIES", nativeQuery = true)
  List<ICountryResponse> findAllCountries();

  @Query(value = "CALL SP_GET_CITIES", nativeQuery = true)
  List<ICityResponse> findAllCities();

  /*@Query(value = "CALL SP_GET_ALL_TECHNICAL_SKILLS", nativeQuery = true)
  UserList<Map<String, String>> findAllTechnicalSkills();*/

  @Query(value = "CALL SP_GET_ALL_TECHNICAL_SKILLS", nativeQuery = true)
  List<String> findAllTechnicalSkills();

}
