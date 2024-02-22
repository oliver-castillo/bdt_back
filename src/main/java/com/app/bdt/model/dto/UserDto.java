package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;

  private String name;

  private String paternalSurname;

  private String maternalSurname;

  private String image;

  private String username;

  private String password;

}
