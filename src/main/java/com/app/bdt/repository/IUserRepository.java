package com.app.bdt.repository;

import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.IListUserTalentResponse;
import com.app.bdt.model.response.IUserAndRole;
import com.app.bdt.model.response.IUserTalentList;
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
  @Query(value = "CALL SP_INSERT_LIST(:#{#userList.userId}, :#{#userList.listName})", nativeQuery = true)
  void addList(@Param("userList") UserListRequest userListRequest);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_INSERT_TALENT_TO_LIST(:#{#obj.listId}, :#{#obj.talentId})", nativeQuery = true)
  void addTalentToList(@Param("obj") UserTalentListRequest userTalentListRequest);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_UPDATE_USER_TALENT_LIST(:#{#obj.id}, :#{#obj.listId}, :#{#obj.talentId})", nativeQuery = true)
  void updateUserTalentList(@Param("obj") UserTalentListRequest userTalentListRequest);

  @Query(value = "CALL SP_GET_LIST_USER_TALENT_BY_ID(:id)", nativeQuery = true)
  Optional<IUserTalentList> getUserTalentListById(@Param("id") Long id);

  @Query(value = "CALL SP_GET_LISTS_BY_USER_ID(:userId)", nativeQuery = true)
  List<IListUserTalentResponse> findListsByUserId(@Param("userId") Long userId);

  @Query(value = "CALL SP_GET_USER_BY_ID(:userId)", nativeQuery = true)
  Optional<User> findUserById(@Param("userId") Long userId);

  @Transactional
  @Modifying
  @Query(value = "CALL SP_DELETE_TALENT_OF_LIST(:listId, :talentId)", nativeQuery = true)
  void deleteTalentFromList(@Param("listId") Long listId, @Param("talentId") Long talentId);

}
