package com.matheustirabassi.pizzainbox.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.State;
import com.matheustirabassi.pizzainbox.dto.CityDto;
import com.matheustirabassi.pizzainbox.dto.StateDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceIntegrationTest {

  @Autowired
  CustomerService customerService;

  // region findAllStates tests

  @Sql(scripts = {"/create_states.sql"})
  @Test
  @DisplayName("Busca todos os estados e retorna a lista")
  public void findAllStatesTest_AllValid_Sucess() {
    State expectedState1 = new State();
    expectedState1.setName("SP");
    State expectedState2 = new State();
    expectedState2.setName("MG");
    List<StateDto> states = customerService.findAllStates();
    assertAll(() -> assertThat(expectedState1.getName(), is(states.get(0).getName())),
        () -> assertThat(expectedState2.getName(), is(states.get(1).getName())));

  }
  // endregion

  // region findCityByStateId

  @Sql(scripts = {"/create_cities.sql"})
  @Test
  @DisplayName("Busca todas as cidades pelo id do estado")
  public void findCityByStateId() {
    City expectedCity1 = new City();
    expectedCity1.setName("Cerquilho");
    City expectedCity2 = new City();
    expectedCity2.setName("Boituva");
    List<CityDto> cities = customerService.findAllByStateId(1L);
    assertAll(() -> assertThat(expectedCity1.getName(), is(cities.get(0).getName())),
        () -> assertThat(expectedCity2.getName(), is(cities.get(1).getName())));
  }
  // endregion

}