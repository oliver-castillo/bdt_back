package com.app.bdt.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonIgnore
  private byte[] image;

  @JsonProperty("image")
  private String decodedImage;

  public String getDecodedImage() {
    decodedImage = (image != null) ? new String(image) : "unknown";
    return decodedImage;
  }
}
