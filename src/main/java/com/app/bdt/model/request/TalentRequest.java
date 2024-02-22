package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentRequest {

  @NotBlank(message = "El nombre es requerido")
  @Length(min = 3, message = "El nombre debe contener más de 3 caracteres")
  private String name;

  @NotBlank(message = "El apellido paterno es requerido")
  @Length(min = 3, message = "El apellido paterno debe contener más de 3 caracteres")
  private String paternalSurname;

  @NotBlank(message = "El apellido materno es requerido")
  @Length(min = 3, message = "El apellido materno debe contener más de 3 caracteres")
  private String maternalSurname;

  @NotBlank(message = "La imagen es requerida")
  private String image;

  @NotBlank(message = "La descripción es requerida")
  @Length(min = 3, message = "La descripción debe contener más de 3 caracteres.")
  private String description;

  @NotBlank(message = "El monto inicial es requerido")
  private Double initialAmount;

  @NotBlank(message = "El monto final es requerido")
  private Double finalAmount;

  @NotBlank(message = "El número de celular del talento es requerido")
  private String cellPhoneNumber;

  @NotBlank(message = "El link del LinkedIn del talento es requerido")
  private String linkedinLink;

  @NotBlank(message = "El link de GitHub del talento es requerido")
  private String githubLink;

  @NotBlank
  private List<SoftSkillRequest> softSkillList;
  private List<TechnicalSkillRequest> technicalSkillList;

  private List<WorkExperienceRequest> workExperienceList;
  private List<EducationalExperienceRequest> educationalExperienceList;
}
