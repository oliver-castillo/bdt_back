package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentRequest {

  @NotNull(message = "El nombre es requerido")
  @Length(min = 3, message = "El nombre debe contener más de 3 caracteres")
  private String name;

  @NotNull(message = "El apellido paterno es requerido")
  @Length(min = 3, message = "El apellido paterno debe contener más de 3 caracteres")
  @Pattern(regexp = "^[^0-9]+$", message = "Ingrese datos válidos")
  private String paternalSurname;

  @NotNull(message = "El apellido materno es requerido")
  @Length(min = 3, message = "El apellido materno debe contener más de 3 caracteres")
  @Pattern(regexp = "^[^0-9]+$", message = "Ingrese datos válidos")
  private String maternalSurname;

  @NotNull
  @Min(value = 1, message = "Ingrese el ID de un país válido")
  @Max(value = 2, message = "Ingrese el ID de un país válido")
  private int countryId;

  @NotNull
  @Min(value = 1, message = "Ingrese el ID de un país válido")
  @Max(value = 3, message = "Ingrese el ID de un país válido")
  private int cityId;

  @NotNull
  @Min(value = 1, message = "Ingrese el ID de un país válido")
  @Max(value = 2, message = "Ingrese el ID de un país válido")
  private int currencyId;

  @NotNull
  @Min(value = 1, message = "Ingrese el ID de un país válido")
  @Max(value = 4, message = "Ingrese el ID de un país válido")
  private int profileId;

  @NotNull(message = "La imagen es requerida")
  private String image;

  @NotNull(message = "La descripción es requerida")
  @Length(min = 10, message = "La descripción debe contener 10 o más caracteres.")
  private String description;

  @NotNull(message = "El monto inicial es requerido")
  @Min(value = 1000, message = "El monto inicial es requ")
  private Double initialAmount;

  @NotNull(message = "El monto final es requerido")
  private Double finalAmount;

  @NotNull(message = "El número de celular del talento es requerido")
  private String cellPhoneNumber;

  @NotNull(message = "El link del LinkedIn del talento es requerido")
  private String linkedinLink;

  @NotNull(message = "El link de GitHub del talento es requerido")
  private String githubLink;

  @NotEmpty
  private List<SoftSkillRequest> softSkillsList;

  @NotEmpty
  private List<TechnicalSkillRequest> technicalSkillsList;

  @NotEmpty
  private List<WorkExperienceRequest> workExperiencesList;

  @NotEmpty
  private List<EducationalExperienceRequest> educationalExperiencesList;

  @NotEmpty
  private List<LanguageRequest> languagesList;

  @NotEmpty
  private List<FileRequest> filesList;
}
