package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import java.util.List;

public interface LoginService extends GenericService<Login> {

  public Login findByLogin(String user);

  public Login fromDto(NewLoginDto dto);

  public List<NewLoginDto> findAllWithCliente();
}