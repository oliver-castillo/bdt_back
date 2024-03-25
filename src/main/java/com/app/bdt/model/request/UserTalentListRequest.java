package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTalentListRequest {
  private Long id;
  @NotNull
  private Long listId;
  @NotNull
  private Long talentId;
}
