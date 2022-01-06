package com.matheustirabassi.pizzainbox.domain.enums;

public enum PermissionStatus {

  CUSTOMER(1, "ADMINISTRATOR"), ADMIN(2, "Administrador");

  private int cod;
  private String description;

  private PermissionStatus(int cod, String description) {
    this.cod = cod;
    this.description = description;
  }

  public static PermissionStatus toEnum(Integer tipo) {
    if (tipo == null) {
      return null;
    }
    for (PermissionStatus x : PermissionStatus.values()) {
      if (tipo.equals(x.getCod())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + tipo);
  }

  public int getCod() {
    return cod;
  }

  public String getDescription() {
    return description;
  }

}