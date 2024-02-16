package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.UserList;

@Repository
public interface IUserListRepository extends JpaRepository<UserList, Long> {

}
