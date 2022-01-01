package com.matheustirabassi.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheustirabassi.cursomc.domain.enums.StatusPermissao;
import com.matheustirabassi.cursomc.domain.enums.TipoCliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  @BatchSize(size = 1000)
  List<Endereco> enderecos = new ArrayList<>();
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;
  private Integer statusPermissao;
  @ElementCollection
  @CollectionTable(name = "telefone")
  @Fetch(FetchMode.JOIN)
  private Set<String> telefones = new HashSet<>();

  @JsonIgnore
  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos = new ArrayList<>();
  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL)
  private Login login;

  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public StatusPermissao getStatusPermissao() {
    return StatusPermissao.toEnum(statusPermissao);
  }

  public void setStatusPermissao(StatusPermissao statusPermissao) {
    this.statusPermissao = statusPermissao.getCod();
  }



}