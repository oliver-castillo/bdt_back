package com.app.bdt.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;
  private String name;
  private String paternalSurname;
  private String maternalSurname;
  private String username;
  @JsonIgnore
  private String password;
  private List<RoleDto> roles;
  private String image;

  public UserDto(String username, String password) {
    this.username = username;
    this.password = password;
  }

}
