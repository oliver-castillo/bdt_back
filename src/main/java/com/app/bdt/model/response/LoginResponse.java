package com.app.bdt.model.response;

import com.app.bdt.model.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
  private UserDto userDto;
  private String token;
}
