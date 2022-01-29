package com.matheustirabassi.pizzainbox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.matheustirabassi.pizzainbox.dao.CityRepository;
import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dao.LoginRepository;
import com.matheustirabassi.pizzainbox.dao.StateRepository;
import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.State;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import com.matheustirabassi.pizzainbox.services.exceptions.ServiceException;
import com.matheustirabassi.pizzainbox.services.impl.CustomerServiceImpl;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CustomerServiceTest {

  @Mock
  CustomerRepository customerRepository;

  @Mock
  LoginRepository loginRepository;

  @Mock
  StateRepository stateRepository;

  @Mock
  CityRepository cityRepository;

  @InjectMocks
  CustomerServiceImpl customerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  // region Save Tests

  @Test
  @DisplayName("Envia um cliente válido e salva o cliente")
  void saveTest_allValid_sucess() {

    NewCustomerDto customer = new NewCustomerDto();
    customer.setLogin(new NewLoginDto());
    customerService.save(customer);
  }

  @Test
  @DisplayName("Tenta salvar um cliente com e-mail existente e lança exceção")
  void saveTest_EmailAlreadyExists_ServiceException() {
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
  @DisplayName("Tenta salvar um um cliente com login existente e lança exceção")
  void saveTest_LoginAlreadyExists_ServiceException() {
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

  // endregion

  // region updateCustomer tests

  @Test
  @DisplayName("Atualiza um cliente com e-mail existente e lança exceção")
  void updateCustomerTest_EmailAlreadyExists_ServiceException() {
    final String existingEmail = "email@email.com";
    when(customerRepository.findByEmailExists(existingEmail)).thenReturn(true);

    CustomerDto customer = new CustomerDto();
    customer.setEmail(existingEmail);

    Exception exception = assertThrows(ServiceException.class,
        () -> customerService.updateCustomer(customer));
    String expectedMessage = "Esse email já existe!";
    assertTrue(
        exception.getMessage().contains(expectedMessage));
  }

  // endregion

  // region findAllStates tests

  @Test
  @DisplayName("Busca todos os estados e retorna a lista")
  public void findAllStatesTest_AllValid_Sucess() {
    State state1 = new State();
    state1.setName("SP");
    State state2 = new State();
    state2.setName("RJ");
    List<State> expectedStates = Arrays.asList(state1, state2);
    when(stateRepository.findAll()).thenReturn(expectedStates);
    assertEquals(expectedStates, customerService.findAllStates());
  }
  // endregion

  // region findCityByStateId
  @Test
  @DisplayName("Busca todas as cidades pelo id do estado")
  public void findCityByStateId() {
    City city = new City();
    city.setName("Cerquilho");
    State state = new State();
    city.setState(state);
    state.setName("SP");
    state.setId(1L);
    List<City> expectedCities = Collections.singletonList(city);
    when(cityRepository.findAllByStateId(state.getId())).thenReturn(expectedCities);
    assertEquals(expectedCities, customerService.findAllByStateId(state.getId()));
  }

  // endregion
}