package com.matheustirabassi.pizzainbox.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe de endereço para o cliente.
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_address")
public class Address extends BaseEntity {

  private String street;
  private String number;
  private String complement;
  private String district;
  private String cep;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "city_id")
  private City city;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Address(String street, String number, String complement, String district,
      String cep, City city) {
    this.street = street;
    this.number = number;
    this.complement = complement;
    this.district = district;
    this.cep = cep;
    this.city = city;
  }
}