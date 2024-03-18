package com.app.bdt.config.security.controller;

import com.app.bdt.config.security.dto.UserPrincipal;
import com.app.bdt.config.security.jwt.JWTUtil;
import com.app.bdt.model.request.LoginRequest;
import com.app.bdt.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("")
public class AuthController {

  private final IUserService userService;
  private final AuthenticationManager authenticationManager;

  /*@GetMapping()
  ResponseEntity<Object> data(@AuthenticationPrincipal User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }*/

  @GetMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserPrincipal getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() instanceof UserPrincipal) {
      return (UserPrincipal) auth.getPrincipal();
    }
    return null;
  }

  @PostMapping("/login")
  ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest, @AuthenticationPrincipal User user) {
    try {
      Authentication auth = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      loginRequest.getUsername(),
                      loginRequest.getPassword())
      );
      String token = JWTUtil.generateToken(auth.getName());
      Map<String, Object> response = new HashMap<>();
      response.put("user", user);
      response.put("token", token);
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (BadCredentialsException e) {
      System.out.println(e.getMessage());
      throw new BadCredentialsException(e.getMessage());
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      throw new RuntimeException(e.getMessage());
    }
  }
}
