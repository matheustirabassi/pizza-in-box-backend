package com.matheustirabassi.cursomc.domain.enums;

public enum StatusPermissao {

	CLIENTE(1, "Cliente"), ADMINISTRADOR(2, "Administrador");

	private int cod;
	private String descricao;

	private StatusPermissao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusPermissao toEnum(Integer tipo) {
		if (tipo == null) {
			return null;
		}
		for (StatusPermissao x : StatusPermissao.values()) {
			if (tipo.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + tipo);
	}

}
