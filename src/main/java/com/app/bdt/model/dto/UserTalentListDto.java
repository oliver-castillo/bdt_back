package com.app.bdt.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTalentListDto {
  @JsonProperty("idInList")
  private Long id;
  private UserListDto userList;
}
