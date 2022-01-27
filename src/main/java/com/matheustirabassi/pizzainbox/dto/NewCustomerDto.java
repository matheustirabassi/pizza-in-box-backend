package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.enums.DocumentType;
import com.matheustirabassi.pizzainbox.services.annotations.DocumentBr;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Classe intermediária para o cliente
 */
@NoArgsConstructor
@Data
public class NewCustomerDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
  private String name;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Email(message = "O email é inválido")
  private String email;

  @NotEmpty(message = "Preenchimento obrigatório")
  @DocumentBr
  private String document;

  @NotNull(message = "Preenchimento obrigatório")
  private Integer documentType;

  @Size(min = 1, max = 2, message = "Somente é possível adicionar no máximo dois telefones")
  private Set<String> cellphones;

  @Size(min = 1, max = 2, message = "Somente é possível adicionar no máximo dois endereços")
  private List<AddressDto> addresses;

  @NotNull(message = "Preenchimento obrigatório")
  private NewLoginDto login;

  /**
   * Convert of Customer for NewCustomerDto.
   *
   * @param customer the customer.
   */
  public NewCustomerDto(Customer customer) {
    this.name = customer.getName();
    this.email = customer.getEmail();
    this.document = customer.getDocument();
    this.setDocumentType(customer.getDocumentType());
    this.cellphones = customer.getCellphones();
    this.addresses = AddressDto.convertList(customer.getAddresses());
    this.login = new NewLoginDto(customer.getLogin());
  }

  public static List<NewCustomerDto> convertList(List<Customer> customers) {
    return customers.stream().map(NewCustomerDto::new).collect(Collectors.toList());
  }

  public DocumentType getDocumentType() {
    return DocumentType.toEnum(documentType);
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType.getCod();
  }
}