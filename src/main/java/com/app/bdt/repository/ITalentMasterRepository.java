package com.app.bdt.repository;

import com.app.bdt.model.entity.TalentMaster;
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
  @Query(value = "CALL SP_INSERT_COUNTRY_TALENT(:talentId, 1, :#{#talentMaster.id})", nativeQuery = true)
  void addCountry(@Param("talentId") Long talentId, @Param("talentMaster") TalentMaster talentMaster);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_CITY_TALENT(:talentId, 2, :#{#talentMaster.id})", nativeQuery = true)
  void addCity(@Param("talentId") Long talentId, @Param("talentMaster") TalentMaster talentMaster);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_CURRENCY_TALENT(:talentId, 4, :#{#talentMaster.id})", nativeQuery = true)
  void addCurrency(@Param("talentId") Long talentId, @Param("talentMaster") TalentMaster talentMaster);

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_PROFILE_TALENT(:talentId, 5, :#{#talentMaster.id})", nativeQuery = true)
  void addProfile(@Param("talentId") Long talentId, @Param("talentMaster") TalentMaster talentMaster);

  @Query(value = "CALL SP_GET_TALENT_MASTER_DATA", nativeQuery = true)
  List<ITalentMasterDataResponse> getMasterDataOfTalents();

}
