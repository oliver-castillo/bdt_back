package com.app.bdt.config.security.dto;

import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
  private Long id;
  private String name;
  private String paternalSurname;
  private String maternalSurname;
  private String image;
  private List<String> roles;

  private String username;
  @JsonIgnore
  private String password;
  private Collection<? extends GrantedAuthority> authorities;
  private boolean isAccountNonExpired;
  private boolean isAccountNonLocked;
  private boolean isCredentialsNonExpired;
  private boolean isEnabled;

  public static UserPrincipal build(UserDto userDto) {
    List<String> roles = userDto.getRoles().stream().map(RoleDto::getRole).collect(Collectors.toList());
    List<GrantedAuthority> authorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
    return new UserPrincipal(
            userDto.getId(),
            userDto.getName(),
            userDto.getPaternalSurname(),
            userDto.getMaternalSurname(),
            userDto.getImage(),
            roles,
            userDto.getUsername(),
            userDto.getPassword(),
            authorities,
            true, true, true, true);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
