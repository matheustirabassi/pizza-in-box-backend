package com.matheustirabassi.pizzainbox.domain.enums;

public enum PaymentStatus {

  PENDING(1, "Pendente"), PAID(2, "Quitado"), CANCELED(3, "Cancelado");

  private int cod;
  private String description;

  private PaymentStatus(int cod, String description) {
    this.cod = cod;
    this.description = description;
  }

  public static PaymentStatus toEnum(Integer tipo) {
    if (tipo == null) {
      return null;
    }
    for (PaymentStatus x : PaymentStatus.values()) {
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