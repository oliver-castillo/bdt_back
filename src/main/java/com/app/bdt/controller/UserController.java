package com.app.bdt.controller;

import com.app.bdt.model.request.UserRequest;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final IUserService userService;

  @PostMapping("/user")
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{username}")
  public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
  }

}
