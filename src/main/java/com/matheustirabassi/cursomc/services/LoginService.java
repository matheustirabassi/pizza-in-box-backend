package com.matheustirabassi.cursomc.services;

import java.util.List;

import com.matheustirabassi.cursomc.domain.Login;
import com.matheustirabassi.cursomc.dto.LoginDto;

public interface LoginService extends GenericService<Login> {
  public Login findByLogin(String user);

  public Login fromDto(LoginDto dto);

  public LoginDto saveLoginWithClienteId(LoginDto login, Integer clienteId);

  public List<Login> findAllWithCliente();
}