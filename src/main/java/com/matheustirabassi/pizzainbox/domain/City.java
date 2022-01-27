package com.matheustirabassi.pizzainbox.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe de cidade para cliente.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "city")
public class City extends BaseEntity {

  private String name;

  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "state_id")
  private State state;
}