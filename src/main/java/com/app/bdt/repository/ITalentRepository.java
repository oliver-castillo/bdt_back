package com.app.bdt.repository;

import com.app.bdt.model.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ITalentRepository extends JpaRepository<Talent, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_TALENT(:#{#talent.name}, :#{#talent.paternalSurname}, :#{#talent.maternalSurname}, :#{#talent.image}, :#{#talent.description}, :#{#talent.initialAmount}, :#{#talent.finalAmount}, :#{#talent.cellPhoneNumber}, :#{#talent.linkedinLink}, :#{#talent.githubLink})", nativeQuery = true)
  void createTalent(@Param("talent") Talent talent);

  @Query(value = "CALL SP_GET_LAST_INSERTED_TALENT", nativeQuery = true)
  Talent getLastInsertedTalent();

  @Query(value = "CALL SP_GET_TALENTS", nativeQuery = true)
  List<Talent> findAllTalents();

}
