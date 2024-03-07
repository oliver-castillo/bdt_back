package com.app.bdt.util;

public enum Messages {

  NOT_FOUND("Registro no encontrado"),
  SUCCESSFUL_UPDATE("Actualización realizada correctamente"),
  SUCCESSFUL_INSERT("Registro realizado correctamente"),
  INVALID_DATA("Ingrese datos válidos"),
  ALREADY_REGISTERED("Los datos ingresados ya se encuentran registrados"),
  REPEATED_DATA("Los datos contienen valores duplicados");

  private final String message;

  Messages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

