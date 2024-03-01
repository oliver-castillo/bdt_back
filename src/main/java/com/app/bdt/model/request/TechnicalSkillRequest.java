package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkillRequest {

  @NotNull
  @Pattern(regexp = "^[^0-9]+$", message = "Ingrese datos válidos")
  private String skill;

  @NotNull
  @Min(value = 1)
  @Max(value = 50)
  @Digits(integer = 2, fraction = 0)
  private Integer years;

}
