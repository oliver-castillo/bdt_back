package com.app.bdt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

  private final HttpServletRequest request;
  private Map<String, String> errores = new HashMap<>();

  public GlobalExceptionHandler(HttpServletRequest request) {
    this.request = request;
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex, HttpServletRequest request) {
    return handleException(ex, request, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> badRequestExceptionHandler(Exception ex, HttpServletRequest request) {
    return handleException(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(InternalServerError.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<Object> internalServerErrorExceptionHandler(Exception ex, HttpServletRequest request) {
    return handleException(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request, HttpStatus status) {
    ExceptionInfo exceptionInfo = buildExceptionInfo(ex, request, status);
    return new ResponseEntity<>(exceptionInfo, status);
  }

  private ExceptionInfo buildExceptionInfo(Exception ex, HttpServletRequest request, HttpStatus status) {
    ExceptionInfo exceptionInfo = new ExceptionInfo();
    exceptionInfo.setTimestamp(LocalDateTime.now());
    exceptionInfo.setStatusCode(status.value());
    exceptionInfo.setStatusName(status.getReasonPhrase());
    exceptionInfo.setMessage(ex.getMessage());
    exceptionInfo.setUriRequested(request.getRequestURI());
    return exceptionInfo;
  }

}
