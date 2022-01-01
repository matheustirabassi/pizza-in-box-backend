package com.matheustirabassi.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que compõe o endereço do cliente com a cidade
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Estado {

  @JsonIgnore
  @OneToMany(mappedBy = "estado")
  List<Cidade> cidades = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
}