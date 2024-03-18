package com.app.bdt.config.security.dto;

import lombok.Data;

@Data
public class JwtDto {
  private UserPrincipal userPrincipal;
  private String bearer = "Bearer";
  private String token;

  public JwtDto(UserPrincipal userPrincipal, String token) {
    this.userPrincipal = userPrincipal;
    this.token = token;
  }
}
