package com.app.bdt.config.security.service;

import com.app.bdt.config.security.dto.UserPrincipal;
import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
  private final IUserService userService;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDto userDto = userService.getUserByUsername(username);
    if (userDto == null) {
      throw new UsernameNotFoundException("El usuario no se encuentra registrado");
    }
    List<String> roles = new ArrayList<>();
    for (RoleDto role : userDto.getRoles()) {
      roles.add(role.getRole());
    }

    Set<GrantedAuthority> authorities = new HashSet<>();
    for (String role : roles) {
      authorities.add(new SimpleGrantedAuthority(role));
    }

    return UserPrincipal.build(userDto);
  }

  /*return org.springframework.security.core.userdetails.User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .roles(roles.toArray(new String[0]))
            .build();*/
}
