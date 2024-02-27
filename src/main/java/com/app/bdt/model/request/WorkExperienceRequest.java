package com.app.bdt.model.request;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceRequest {

  @NotBlank(message = "El nombre de la empresa es requerido")
  @Length(min = 3, message = "El nombre debe contener más de 3 caracteres")
  private String company;

  @NotBlank(message = "El puesto es requerido")
  private String position;

  @NotBlank(message = "La fecha de inicio es requerida")
  private LocalDate startDate;

  @NotBlank(message = "La fecha de fin es requerida")
  private LocalDate endDate;
}
