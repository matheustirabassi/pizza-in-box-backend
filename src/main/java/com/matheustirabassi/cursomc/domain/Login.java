package com.matheustirabassi.cursomc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Classe de login para o cliente.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_login")
public class Login {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(unique = true)
  private String username;
  private String password;
  @ToString.Exclude
  @OneToOne
  private Cliente cliente;
}