package com.app.bdt.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_USER(:#{#user.name}, :#{#user.paternalSurname}, :#{#user.maternalSurname}, :#{#user.image}, :#{#user.username}, :#{#user.password})", nativeQuery = true)
  void createUser(@Param("user") User user);
}
