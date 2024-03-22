package com.app.bdt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentCardResponse {
  private Long id;
  private String name;
  private String paternalSurname;
  private String maternalSurname;
  private String country;
  private String city;
  private String currency;
  private String profile;
  private Double initialAmount;
  private Double finalAmount;
  //private List<FeedbackDto> feedbacksList;
  private int averageRating;
  private String image;
}
