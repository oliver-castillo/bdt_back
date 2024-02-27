package com.app.bdt.controller;

import com.app.bdt.model.request.UserRequest;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

  private final IUserService userService;

  @PostMapping("/user")
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
