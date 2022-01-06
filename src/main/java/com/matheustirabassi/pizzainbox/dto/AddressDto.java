package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Address;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class AddressDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String street;
  private String number;
  private String complement;
  private String district;
  private String cep;
  private String city;
  private String state;

  public AddressDto(Address address) {
    this.id = address.getId();
    this.street = address.getStreet();
    this.number = address.getNumber();
    this.complement = address.getComplement();
    this.district = address.getDistrict();
    this.cep = address.getCep();
    this.city = address.getCity().getName();
    this.state = address.getCity().getState().getName();
  }

  public static List<AddressDto> convertList(List<Address> addresses) {
    return addresses.stream().map(AddressDto::new).collect(Collectors.toList());
  }
}