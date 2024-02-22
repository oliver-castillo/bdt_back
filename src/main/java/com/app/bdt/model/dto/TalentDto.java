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

  private String image;

  private String description;

  private Double initialAmount;

  private Double finalAmount;

  private String cellPhoneNumber;

  private String linkedinLink;

  private String githubLink;

  private List<SoftSkillDto> softSkillList;

  private List<TechnicallSkillDto> technicalSkillList;

  private List<WorkExperienceDto> workExperienceList;

  private List<EducationalExperienceDto> educationalExperienceList;
}
