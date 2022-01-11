package com.matheustirabassi.pizzainbox.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.enums.DocumentType;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import com.matheustirabassi.pizzainbox.services.CustomerService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

  @Autowired
  private Validator validator;

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController customerController;

  private NewCustomerDto newCustomerDto;

  private Customer customer;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    newCustomerDto = new NewCustomerDto();
    newCustomerDto.setDocument("12345678909");
    newCustomerDto.setEmail("email@email.com");
    newCustomerDto.setName("name valid");
    newCustomerDto.setDocumentType(DocumentType.PF);

    Set<String> cellphones = new HashSet<>();
    cellphones.add("9999999999");
    newCustomerDto.setCellphones(cellphones);

    AddressDto addressDto = new AddressDto();
    addressDto.setCep("18520000");
    addressDto.setCity(1L);
    List<AddressDto> addresses = new ArrayList<>();
    addresses.add(addressDto);
    newCustomerDto.setAddresses(addresses);

    NewLoginDto newLoginDto = new NewLoginDto();
    newLoginDto.setUser("valid user");
    newLoginDto.setPassword("valid password");
    newCustomerDto.setLogin(newLoginDto);

    customer = new Customer();
    customer.setId(1L);

  }

  //region insertTest

  @Test
  @DisplayName("Insere um cliente válido no sistema")
  void insertTest_AllValid_Sucess() throws Exception {
    when(customerService.save(any(NewCustomerDto.class))).thenReturn(customer);
    validateMock(newCustomerDto);
    ResponseEntity<?> response = customerController.insert(newCustomerDto);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    String expectedURI = "http://localhost/1";
    assertEquals(expectedURI, Objects.requireNonNull(response.getHeaders().get("Location")).get(0));
  }

  @Test
  @DisplayName("Insere um cliente com documento inválido")
  void insertTest_DocumentInvalid_ValidationException() throws Exception {
    String invalidDocument = "1";
    newCustomerDto.setDocument(invalidDocument);
    assertThrows(ValidationException.class, () -> validateMock(newCustomerDto));

  }

  //endregion

  private void validateMock(Object bean) throws AssertionError {
    Optional<ConstraintViolation<Object>> violation = validator.validate(bean).stream().findFirst();
    if (violation.isPresent()) {
      throw new ValidationException(violation.get().getMessage());
    }
  }
}