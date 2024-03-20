package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListUserDto {
  private Long userId;
  private List<ListUserTalentDto> lists;
}
