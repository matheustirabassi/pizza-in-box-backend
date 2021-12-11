package com.matheustirabassi.cursomc.dto;

import javax.persistence.Column;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheustirabassi.cursomc.domain.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto {
	private Integer id;
	@Column(unique = true)
	private String user;
	private String password;
	@JsonIgnore
	@OneToOne
	private Cliente cliente;
}
