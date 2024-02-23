package com.app.bdt.repository;

import com.app.bdt.model.entity.LanguageTalentMaster;
import com.app.bdt.model.response.ILanguagesTalent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ILanguageTalentMasterRepository extends JpaRepository<LanguageTalentMaster, Long> {

  @Modifying
  @Transactional
  @Query(value = "CALL SP_INSERT_TALENT_LANGUAGE(:talentId, :#{#languageTalentMaster.languageId}, :#{#languageTalentMaster.levelId}, :#{#languageTalentMaster.numberOfStars})", nativeQuery = true)
  void createMasterTalentLanguage(@Param("talentId") Long talentId, @Param("languageTalentMaster") LanguageTalentMaster languageTalentMaster);

  @Query(value = "CALL SP_GET_LANGUAGES_TALENTS", nativeQuery = true)
  List<ILanguagesTalent> findAllLanguagesTalents();
}
