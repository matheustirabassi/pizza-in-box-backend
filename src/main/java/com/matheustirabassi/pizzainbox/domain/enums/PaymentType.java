package com.matheustirabassi.pizzainbox.domain.enums;

public enum PaymentType {

  CARD(1, "Cartão de crédito"), BANKSLIP(2, "Boleto");

  private long cod;
  private String description;

  private PaymentType(int cod, String description) {
    this.cod = cod;
    this.description = description;
  }

  public static PaymentType toEnum(Long type) {
    if (type == null) {
      return null;
    }
    for (PaymentType x : PaymentType.values()) {
      if (type.equals(x.getCod())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + type);
  }

  public long getCod() {
    return cod;
  }

  public String getDescription() {
    return description;
  }

}