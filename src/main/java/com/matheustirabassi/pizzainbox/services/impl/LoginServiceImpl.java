package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.LoginRepository;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.LoginDto;
import com.matheustirabassi.pizzainbox.services.LoginService;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends GenericServiceImpl<Login> implements LoginService {

  @Autowired
  transient LoginRepository loginRepository;
  @Autowired
  transient CustomerRepository customerRepository;

  @Override
  public Login findByLogin(String user) {
    Optional<Login> obj = loginRepository.findByUser(user);
    return obj.orElse(null);
  }

  @Override
  public Login saveOrUpdate(Login login) {
    return loginRepository.save(login);
  }

  public LoginDto saveLoginWithClienteId(LoginDto dto, Long customerId) {
    try {
      Login login = fromDto(dto);
      Customer customer = customerRepository.findById(customerId).orElse(null);
      customer.setLogin(login);
      customer.setPermissionStatus(dto.getPermissionStatus());
      login.setCustomer(customer);
      saveOrUpdate(login);
      customerRepository.save(customer);
      return new LoginDto(login);
    } catch (Exception e) {
      throw new ObjectNotFoundException("Cliente não encontrado ou login duplicado!");
    }
  }

  @Override
  protected GenericRepository<Login> getDAO() {
    return loginRepository;
  }

  @Override
  public Login fromDto(LoginDto dto) {
    Login login = new Login(dto.getUser(), dto.getPassword(), null);
    login.setId(dto.getId());
    return login;

  }

  @Override
  public List<LoginDto> findAllWithCliente() {
    List<LoginDto> loginDtoList = LoginDto.convertList(loginRepository.findAllWithCliente());
    return loginDtoList;
  }
}