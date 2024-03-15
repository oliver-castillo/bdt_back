package com.app.bdt.controller;

import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class AuthController {

  private final IUserService userService;

  @GetMapping("/login")
  ResponseEntity<Object> login() {
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
