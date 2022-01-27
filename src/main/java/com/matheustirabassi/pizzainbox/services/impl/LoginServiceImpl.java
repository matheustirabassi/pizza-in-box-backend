package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.LoginRepository;
import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import com.matheustirabassi.pizzainbox.services.LoginService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends GenericServiceImpl<Login> implements LoginService {

  @Autowired
  transient LoginRepository loginRepository;

  @Override
  public Login findByLogin(String user) {
    Optional<Login> obj = loginRepository.findByUser(user);
    return obj.orElse(null);
  }

  @Override
  protected GenericRepository<Login> getDao() {
    return loginRepository;
  }

  @Override
  public Login fromDto(NewLoginDto dto) {
    Login login = new Login(dto.getUser(), dto.getPassword(), null);
    return login;

  }

  @Override
  public List<NewLoginDto> findAllWithCliente() {
    List<NewLoginDto> newLoginDtoList = NewLoginDto.convertList(
        loginRepository.findAllWithCliente());
    return newLoginDtoList;
  }
}