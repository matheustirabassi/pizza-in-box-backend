package com.matheustirabassi.pizzainbox.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Classe de login para o cliente.
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "login")
public class Login extends BaseEntity {

  @Column(unique = true)
  @NonNull
  private String username;
  @NonNull
  private String password;
  @ToString.Exclude
  @OneToOne
  private Customer customer;
}