package com.app.bdt.service;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.request.LoginRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.response.LoginResponse;

public interface IUserService {

  void createUser(UserRequest userRequest);

  UserDto getUserByUsername(String username);

  LoginResponse validateLogin(LoginRequest loginRequest);
}
