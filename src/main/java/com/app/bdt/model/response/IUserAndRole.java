package com.app.bdt.model.response;

import org.springframework.beans.factory.annotation.Value;

public interface IUserAndRole {
  Long getId();

  @Value("#{target.USER_ID}")
  Long getUserId();

  String getUsername();

  @Value("#{target.ROLE_ID}")
  Integer getRoleId();

  @Value("#{target.ROLE}")
  String getRoleName();
}
