package com.app.bdt.service.serviceImpl;

import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final IUserService userService;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    UserDto userDto = userService.getUserByUsername(s);
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
