package com.matheustirabassi.pizzainbox.domain.enums;

public enum DocumentType {

  PF(1, "Pessoa física"), PJ(2, "Pessoa jurídica");

  private int cod;
  private String description;

  private DocumentType(int cod, String description) {
    this.cod = cod;
    this.description = description;
  }

  public static DocumentType toEnum(Integer type) {
    if (type == null) {
      return null;
    }
    for (DocumentType x : DocumentType.values()) {
      if (type.equals(x.getCod())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + type);
  }

  public int getCod() {
    return cod;
  }

  public String getDescription() {
    return description;
  }

}