package com.matheustirabassi.pizzainbox.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Classe que compõe o endereço do cliente com a cidade
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "state")
public class State extends BaseEntity {

  private String name;

  @ToString.Exclude
  @OneToMany(mappedBy = "state")
  List<City> cities = new ArrayList<>();

}