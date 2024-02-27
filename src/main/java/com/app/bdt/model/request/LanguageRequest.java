package com.app.bdt.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRequest {

  @NotBlank
  @Min(value = 1, message = "Ingrese el ID de un idioma válido")
  @Max(value = 3, message = "Ingrese el ID de un idioma válido")
  private int languageId;

  @NotBlank
  @Min(value = 1, message = "Ingrese el ID de un nivel válido")
  @Max(value = 4, message = "Ingrese el ID de un nivel válido")
  private int levelId;

  @NotBlank
  @Min(value = 1, message = "Ingrese un número de estrellas válido")
  @Max(value = 5, message = "Ingrese un número de estrellas válido")
  private int numberOfStars;

}
