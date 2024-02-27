package com.app.bdt.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkillRequest {
  @NotBlank
  @Pattern(regexp = "^[^0-9]+$", message = "Ingrese datos válidos")
  private String skill;

  @NotBlank
  private int years;
}
