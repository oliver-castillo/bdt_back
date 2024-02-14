package com.app.bdt.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

  private final HttpServletRequest request;
  private Map<String, String> errores = new HashMap<>();

  public GlobalExceptionHandler(HttpServletRequest request) {
    this.request = request;
  }
}
