package com.app.bdt.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {

  @NotNull(message = "El nombre del archivo es requerido")
  private String fileName;

  @NotNull(message = "El tipo de archivo es requerido")
  private String fileType;

  @NotNull(message = "El archivo es requerido")
  private String file;

}
