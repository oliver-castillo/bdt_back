package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentDto {

  private Long id;

  private String name;

  private String paternalSurname;

  private String maternalSurname;

  private String country;

  private String city;

  private String currency;

  private String profile;

  private String description;

  private Double initialAmount;

  private Double finalAmount;

  private String cellPhoneNumber;

  private String linkedinLink;

  private String githubLink;

  private List<SoftSkillDto> softSkillsList;

  private List<TechnicallSkillDto> technicalSkillsList;

  private List<WorkExperienceDto> workExperiencesList;

  private List<EducationalExperienceDto> educationalExperiencesList;

  private List<LanguageDto> languageList;

  private String image;
}
