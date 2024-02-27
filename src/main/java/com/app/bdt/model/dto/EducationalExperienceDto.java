package com.app.bdt.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalExperienceDto {

  private String educationalInstitute;

  private String career;

  private String degree;

  private LocalDate startDate;

  private LocalDate endDate;

}
