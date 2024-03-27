package com.app.bdt.controller;

import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
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

  @PostMapping("/{username}")
  public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
  }

  @PostMapping("/add_list")
  public ResponseEntity<Object> addList(@RequestBody @Valid UserListRequest userListRequest) {
    return new ResponseEntity<>(userService.addList(userListRequest), HttpStatus.CREATED);
  }

  @PostMapping("/add_talent_to_list")
  public ResponseEntity<Object> saveTalentToList(@RequestBody @Valid UserTalentListRequest userTalentListRequest) {

    return new ResponseEntity<>(userService.saveTalentToList(userTalentListRequest), HttpStatus.OK);
  }

  @PostMapping("/lists/{userId}")
  public ResponseEntity<Object> getListsByUserId(@PathVariable Long userId) {
    return new ResponseEntity<>(userService.getListsByUserId(userId), HttpStatus.OK);
  }

}
