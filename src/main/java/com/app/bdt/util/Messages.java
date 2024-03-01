package com.app.bdt.util;

public enum Messages {

  NOT_FOUND("Registro no encontrado"),
  SUCCESSFUL_UPDATE("Actualización realizada correctamente"),
  SUCCESSFUL_INSERT("Registro realizado correctamente");

  private final String message;

  Messages(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}

