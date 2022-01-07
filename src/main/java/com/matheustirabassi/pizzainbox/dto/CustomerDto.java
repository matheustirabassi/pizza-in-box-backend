package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.enums.DocumentType;
import com.matheustirabassi.pizzainbox.domain.enums.PermissionStatus;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe intermediária para o cliente
 */
@NoArgsConstructor
@Data
public class CustomerDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private String email;
  private String document;
  private Integer documentType;
  private Integer permissionStatus;
  private Set<String> cellphones;
  private List<AddressDto> addresses;
  private LoginDto login;

  public CustomerDto(Customer customer) {
    this.id = customer.getId();
    this.name = customer.getName();
    this.email = customer.getEmail();
    this.document = customer.getDocument();
    this.setDocumentType(customer.getDocumentType());
    this.setPermissionStatus(customer.getPermissionStatus());
    this.cellphones = customer.getCellphones();
    this.addresses = AddressDto.convertList(customer.getAddresses());
    this.login = new LoginDto(customer.getLogin());
  }

  public static List<CustomerDto> convertList(List<Customer> customers) {
    return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
  }

  public DocumentType getDocumentType() {
    return DocumentType.toEnum(documentType);
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType.getCod();
  }

  public PermissionStatus getPermissionStatus() {
    return PermissionStatus.toEnum(permissionStatus);
  }

  public void setPermissionStatus(PermissionStatus permissionStatus) {
    this.permissionStatus = permissionStatus.getCod();
  }

}