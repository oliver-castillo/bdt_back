package com.app.bdt.controller;

import com.app.bdt.model.entity.User;
import com.app.bdt.model.entity.UserList;
import com.app.bdt.model.entity.UserTalentList;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.Response;
import com.app.bdt.repository.IUserListRepository;
import com.app.bdt.repository.IUserTalentListRepository;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

  private final IUserService userService;
  private final IUserListRepository userListRepository;
  private final IUserTalentListRepository userTalentListRepository;

  @PostMapping("/user")
  public ResponseEntity<Object> createUser(@Valid @RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{username}")
  public ResponseEntity<Object> getUserByUsername(@PathVariable String username) {
    return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
  }

  @PostMapping("/add_list")
  @ResponseStatus(HttpStatus.CREATED)
  Response addList(@RequestBody @Valid UserListRequest userListRequest) {
    return userService.addList(userListRequest);
  }

  @PostMapping("/add_list_talent")
  @ResponseStatus(HttpStatus.CREATED)
  Response addListTalent(@RequestBody @Valid UserTalentListRequest userTalentListRequest) {
    return userService.addListTalent(userTalentListRequest);
  }

  @GetMapping("/get/{userId}")
  @ResponseStatus(HttpStatus.OK)
  Optional<User> getUser(@PathVariable Long userId) {
    return userService.getUser(userId);
  }

  @GetMapping("/ulist/{id}")
  @ResponseStatus(HttpStatus.OK)
  Optional<UserList> a(@PathVariable Long id) {
    return userListRepository.findById(id);
  }

  @GetMapping("/utlist/{id}")
  @ResponseStatus(HttpStatus.OK)
  Optional<UserTalentList> b(@PathVariable Long id) {
    return userTalentListRepository.findById(id);
  }
}
