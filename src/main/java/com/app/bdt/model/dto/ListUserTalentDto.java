package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUserTalentDto {

  private Long id;

  private String name;

  private Set<Long> talentIds;
}
