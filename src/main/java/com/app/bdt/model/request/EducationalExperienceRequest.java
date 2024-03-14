package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalExperienceRequest {

  @NotNull(message = "El nombre de la institución es requerido")
  @Length(min = 3)
  private String educationalInstitute;

  @NotNull(message = "El nombre de la carrera es requerida")
  @Length(min = 3)
  private String career;

  @NotNull(message = "El tipo de grado es requerida")
  @Length(min = 3)
  private String degree;

  @NotNull(message = "La fecha de inicio es requerida")
  @PastOrPresent
  private LocalDate startDate;

  @NotNull(message = "La fecha de fin es requerida")
  @PastOrPresent
  private LocalDate endDate;

}
