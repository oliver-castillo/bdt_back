package com.app.bdt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBasicDto {

  private Long id;
  private String name;
  private String paternalSurname;
  private String maternalSurname;
  private byte[] image;

}
