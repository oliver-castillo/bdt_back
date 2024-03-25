package com.app.bdt.service;

import com.app.bdt.model.dto.ListUserDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.Response;

public interface IUserService {

  void createUser(UserRequest userRequest);

  UserDto getUserByUserId(Long userId);

  UserDto getUserByUsername(String username);

  Response addList(UserListRequest userListRequest);

  Response addListTalent(Long userId, UserTalentListRequest userTalentListRequest);

  ListUserDto getListsByUserId(Long userId);

}
