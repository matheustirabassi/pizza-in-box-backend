package com.matheustirabassi.pizzainbox.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dao.LoginRepository;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import com.matheustirabassi.pizzainbox.services.exceptions.ServiceException;
import com.matheustirabassi.pizzainbox.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  CustomerRepository customerRepository;

  @Mock
  LoginRepository loginRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void fromDto() {
  }

  @Test
  void fromEnderecoDto() {
  }

  @Test
  void updateCustomer() {
  }

  //region SaveTests

  @Test
  @DisplayName("Envia um cliente válido e salva o cliente")
  void saveTest_allValid_sucess() {

    NewCustomerDto customer = new NewCustomerDto();
    customer.setLogin(new NewLoginDto());
    customerService.save(customer);
  }

  @Test
  @DisplayName("Envia um cliente com e-mail existente e lança exceção")
  void saveTest_EmailAlreadyExists_DataIntegrityException() {
    final String existingEmail = "email@email.com";
    when(customerRepository.findByEmailExists(existingEmail)).thenReturn(true);

    NewCustomerDto customer = new NewCustomerDto();
    customer.setEmail(existingEmail);

    Exception exception = assertThrows(ServiceException.class,
        () -> customerService.save(customer));
    String expectedMessage = "Esse email já existe!";
    assertTrue(
        exception.getMessage().contains(expectedMessage));
  }

  @Test
  @DisplayName("Envia um cliente com e-mail existente e lança exceção")
  void saveTest_LoginAlreadyExists_DataIntegrityException() {
    final String existingUsername = "email@email.com";
    when(loginRepository.findByUsernameExists(existingUsername)).thenReturn(true);

    NewCustomerDto customer = new NewCustomerDto();
    NewLoginDto loginDto = new NewLoginDto();
    loginDto.setUser(existingUsername);
    customer.setLogin(loginDto);

    Exception exception = assertThrows(ServiceException.class,
        () -> customerService.save(customer));
    String expectedMessage = "Esse nome de usuário já existe!";
    assertTrue(
        exception.getMessage().contains(expectedMessage));
  }

  //endregion
}