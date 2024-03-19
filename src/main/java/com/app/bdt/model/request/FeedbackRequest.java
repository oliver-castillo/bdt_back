package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequest {

  @NotNull
  private Long talentId;

  @NotNull
  @Min(1)
  @Max(5)
  private Long starsRating;

  @NotNull
  @Length(min = 10)
  private String description;

  @NotNull
  private Long userId;

}
