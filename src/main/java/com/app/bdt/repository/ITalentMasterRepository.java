package com.app.bdt.repository;

import com.app.bdt.model.entity.TalentMaster;
import com.app.bdt.model.response.ILanguagesTalentResponse;
import com.app.bdt.model.response.ITalentMasterDataResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ITalentMasterRepository extends JpaRepository<TalentMaster, Long> {

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_COUNTRY_TALENT(:talentId, 1, :countryId)", nativeQuery = true)
  void addCountry(@Param("talentId") Long talentId, @Param("countryId") int countryId);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_CITY_TALENT(:talentId, 2, :cityId)", nativeQuery = true)
  void addCity(@Param("talentId") Long talentId, @Param("cityId") int cityId);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_CURRENCY_TALENT(:talentId, 4, :currencyId)", nativeQuery = true)
  void addCurrency(@Param("talentId") Long talentId, @Param("currencyId") int currencyId);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_PROFILE_TALENT(:talentId, 5, :profileId)", nativeQuery = true)
  void addProfile(@Param("talentId") Long talentId, @Param("profileId") int profileId);

  @Query(value = "CALL SP_GET_TALENT_MASTER_DATA", nativeQuery = true)
  List<ITalentMasterDataResponse> getMasterDataOfTalents();

  @Query(value = "CALL SP_GET_LANGUAGES_TALENTS", nativeQuery = true)
  List<ILanguagesTalentResponse> findAllLanguagesOfTalents();

}
