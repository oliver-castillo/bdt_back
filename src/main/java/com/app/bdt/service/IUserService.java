package com.app.bdt.service;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.Response;

import java.util.Optional;

public interface IUserService {

  void createUser(UserRequest userRequest);

  UserDto getUserByUsername(String username);

  Response addList(UserListRequest userListRequest);

  Response addListTalent(UserTalentListRequest userTalentListRequest);

  Optional<User> getUser(Long userId);
}
