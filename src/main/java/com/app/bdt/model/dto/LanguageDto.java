package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {

  private Long id;

  private Integer languageId;

  private String language;

  private Integer levelId;

  private String level;

  private int numberOfStars;

}
