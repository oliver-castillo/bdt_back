package com.app.bdt.controller;

import com.app.bdt.model.request.LoginRequest;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {

  private final IUserService userService;

  @PostMapping("/login")
  ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
    return new ResponseEntity<>(userService.validateLogin(loginRequest), HttpStatus.OK);
  }
}
