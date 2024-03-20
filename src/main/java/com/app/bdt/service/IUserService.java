package com.app.bdt.service;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.Response;

import java.util.Set;

public interface IUserService {

  void createUser(UserRequest userRequest);

  UserDto getUserByUserId(Long userId);

  UserDto getUserByUsername(String username);

  Response addList(UserListRequest userListRequest);

  Response addListTalent(UserTalentListRequest userTalentListRequest);

  Set<Long> getListsByUserId(Long userId);

}
