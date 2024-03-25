package com.app.bdt.repository;

import com.app.bdt.model.entity.UserList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserListRepository extends JpaRepository<UserList, Long> {

}
