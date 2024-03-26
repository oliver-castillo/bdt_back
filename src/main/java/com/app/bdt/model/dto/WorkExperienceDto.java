package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {

  private Long id;
  private String company;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private boolean isCurrent;

}
