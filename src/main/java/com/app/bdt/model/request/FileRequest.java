package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {

  @NotBlank(message = "El nombre del archivo es requerido")
  private String fileName;

  @NotBlank(message = "El tipo de archivo es requerido")
  private String fileType;

  @NotBlank(message = "El archivo es requerido")
  private String file;

}
