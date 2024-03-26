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
  private Integer countryId;
  private Integer cityId;
  private Integer currencyId;
  private Integer profileId;
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
  private List<FeedbackDto> feedbacksList;
  private List<UserTalentListDto> lists;
  private int averageRating;
  private String image = null;
  private List<FileDto> filesList = null;

  public int getAverageRating() {
    if (feedbacksList == null || feedbacksList.isEmpty()) {
      return 0;
    }
    int sum = feedbacksList.stream()
            .mapToInt(FeedbackDto::getStarsNumber)
            .sum();
    averageRating = sum / feedbacksList.size();
    return averageRating;
  }

}
