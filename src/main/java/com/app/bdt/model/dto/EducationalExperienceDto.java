package com.app.bdt.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalExperienceDto {

  private Long id;
  private String educationalInstitute;
  private String career;
  private String degree;
  private LocalDate startDate;
  private LocalDate endDate;
  @JsonProperty("isCurrent")
  private boolean isCurrent;
}
