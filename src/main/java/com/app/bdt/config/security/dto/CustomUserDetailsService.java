package com.app.bdt.config.security.dto;

import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
  private final IUserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = userService.getUserByUsername(username);
    if (userDto == null) {
      throw new UsernameNotFoundException("El usuario no se encuentra registrado");
    }
    List<String> roles = new ArrayList<>();
    for (RoleDto role : userDto.getRoles()) {
      roles.add(role.getRole());
    }
    return org.springframework.security.core.userdetails.User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .roles(roles.toArray(new String[0]))
            .build();
  }
}
