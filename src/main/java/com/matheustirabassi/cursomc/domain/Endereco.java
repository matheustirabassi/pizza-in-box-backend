package com.matheustirabassi.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de endereço para o cliente.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Endereco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "cidade_id")
  private Cidade cidade;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

}