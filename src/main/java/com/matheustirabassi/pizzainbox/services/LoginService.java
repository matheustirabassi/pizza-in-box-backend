package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.LoginDto;
import java.util.List;

public interface LoginService extends GenericService<Login> {

  public Login findByLogin(String user);

  public Login fromDto(LoginDto dto);

  public LoginDto saveLoginWithClienteId(LoginDto login, Long clienteId);

  public List<LoginDto> findAllWithCliente();
}