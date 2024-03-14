package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkillRequest {

  @NotNull
  @Length(min = 1)
  private String skill;

  @NotNull
  @Min(value = 1)
  @Max(value = 50)
  @Digits(integer = 2, fraction = 0)
  private Integer years;

}
