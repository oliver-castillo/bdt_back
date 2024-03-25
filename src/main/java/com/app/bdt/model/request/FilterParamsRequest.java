package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterParamsRequest {
  private Integer languageId;
  private Integer levelId;
  private List<String> technicalSkills;
  private String data;
  private Set<Long> talentsId;
}
