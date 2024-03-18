package com.app.bdt.config.security.controller;

import com.app.bdt.config.security.dto.JwtDto;
import com.app.bdt.config.security.dto.UserPrincipal;
import com.app.bdt.config.security.jwt.JWTProvider;
import com.app.bdt.model.request.LoginRequest;
import com.app.bdt.model.response.Response;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final IUserService userService;
  private final AuthenticationManager authenticationManager;
  private final JWTProvider jwtProvider;

  /*@GetMapping()
  ResponseEntity<Object> data(@AuthenticationPrincipal User user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }*/

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UserPrincipal getUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getPrincipal() instanceof UserPrincipal) {
      return (UserPrincipal) auth.getPrincipal();
    }
    return null;
  }

  @PostMapping("/login")
  public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
    if (bindingResult.hasErrors())
      return new ResponseEntity(new Response(400, "campos mal puestos"), HttpStatus.BAD_REQUEST);
    Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    JwtDto jwtDto = new JwtDto(getUser(), jwt);
    return new ResponseEntity(jwtDto, HttpStatus.OK);
  }

}
