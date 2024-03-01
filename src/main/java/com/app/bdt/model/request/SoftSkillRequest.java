package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftSkillRequest {

  @NotNull
  @NotBlank
  @Pattern(regexp = "^[^0-9]+$", message = "Ingrese datos válidos")
  private String skill;

}
