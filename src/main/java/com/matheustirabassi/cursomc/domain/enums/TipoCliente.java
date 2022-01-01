package com.matheustirabassi.cursomc.domain.enums;

public enum TipoCliente {

  PESSOAFISICA(1, "Pessoa física"), PESSOAJURIDICA(2, "Pessoa jurídica");

  private int cod;
  private String descricao;

  private TipoCliente(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public static TipoCliente toEnum(Integer tipo) {
    if (tipo == null) {
      return null;
    }
    for (TipoCliente x : TipoCliente.values()) {
      if (tipo.equals(x.getCod())) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + tipo);
  }

  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }

}