package com.app.bdt.model.dto;

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
  private boolean isCurrent;
}
