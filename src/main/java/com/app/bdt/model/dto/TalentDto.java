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
  private List<LanguageDto> languagesList;
  private String image;
  private List<FileDto> filesList;

  public TalentDto(Long id, String name, String paternalSurname, String maternalSurname, String country, String city, String currency, String profile, Double initialAmount, Double finalAmount, String image) {
    this.id = id;
    this.name = name;
    this.paternalSurname = paternalSurname;
    this.maternalSurname = maternalSurname;
    this.country = country;
    this.city = city;
    this.currency = currency;
    this.profile = profile;
    this.initialAmount = initialAmount;
    this.finalAmount = finalAmount;
    this.image = image;
  }
}
