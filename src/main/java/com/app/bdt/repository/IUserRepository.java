package com.app.bdt.repository;

import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.IUserAndRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_USER(:#{#user.name}, :#{#user.paternalSurname}, :#{#user.maternalSurname}, :#{#user.image}, :#{#user.username}, :#{#user.password})", nativeQuery = true)
  void createUser(@Param("user") User user);

  @Query(value = "CALL SP_GET_USER_BY_USERNAME(:username)", nativeQuery = true)
  Optional<User> findUserByUsername(@Param("username") String username);

  @Query(value = "CALL SP_GET_USERS_WITH_ROLE", nativeQuery = true)
  List<IUserAndRole> findUsersWithRole();

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_LIST_OF_USER(:#{#userList.userId}, :#{#userList.listName})", nativeQuery = true)
  void addListOfUser(@Param("userList") UserListRequest userListRequest);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_LIST_OF_USER_TALENT(:#{#obj.listId}, :#{#obj.talentId})", nativeQuery = true)
  void addListUserTalent(@Param("obj") UserTalentListRequest userTalentListRequest);

}
