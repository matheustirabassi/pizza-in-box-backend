package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.State;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceIntegrationTest {

  @Autowired
  CustomerService customerService;

  // region findAllStates tests

  @Sql({"/create_state.sql"})
  @Test
  @DisplayName("Busca todos os estados e retorna a lista")
  public void findAllStatesTest_AllValid_Sucess() {
    List<State> states = customerService.findAllStates();
  }
  // endregion

  // region findCityByStateId

  @Sql({"/create_state.sql"})
  @Test
  @DisplayName("Busca todas as cidades pelo id do estado")
  public void findCityByStateId() {
    List<City> cities = customerService.findAllByStateId(1L);
  }
  // endregion

}