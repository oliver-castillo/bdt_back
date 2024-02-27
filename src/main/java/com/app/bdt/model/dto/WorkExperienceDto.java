package com.app.bdt.model.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceDto {

  private String company;

  private String position;

  private LocalDate startDate;

  private LocalDate endDate;

}
