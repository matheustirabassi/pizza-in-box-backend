package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.services.validation.CustomerUpdate;
import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Classe de atualização de cliente.
 */
@CustomerUpdate
@Data
public class CustomerDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório.")
  @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
  private String name;

  @NotEmpty(message = "Preenchimento obrigatório.")
  @Email(message = "email inválido.")
  private String email;

  public CustomerDto() {
  }

  public CustomerDto(Customer customer) {
    id = customer.getId();
    name = customer.getName();
    email = customer.getEmail();
  }

}