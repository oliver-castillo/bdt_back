package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

  @NotBlank
  @Length(min = 3, message = "El nombre debe contener más de 3 caracteres")
  private String name;

  @NotBlank
  @Length(min = 3, message = "El apellido paterno debe contener más de 3 caracteres")
  private String paternalSurname;

  @NotBlank
  @Length(min = 3, message = "El apellido materno debe contener más de 3 caracteres")
  private String maternalSurname;

  @NotBlank
  private String image;

  @NotBlank
  @Length(min = 3, message = "El nombre de usuario debe contener más de 3 caracteres")
  private String username;

  @NotBlank(message = "La contraseña es requerida")
  @Length(min = 3, message = "La contraseña debe contener más de 3 caracteres")
  private String password;

}
