package com.app.bdt.service;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.response.IUserAndRole;

import java.util.List;

public interface IUserService {

  void createUser(UserRequest userRequest);

  UserDto getUserByUsername(String username);

  List<IUserAndRole> T();

}
