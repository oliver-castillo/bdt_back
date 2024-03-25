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
  private List<UserListDto> lists;
  private String image;
  //private List<FeedbackDto> feedbacksList;

}
