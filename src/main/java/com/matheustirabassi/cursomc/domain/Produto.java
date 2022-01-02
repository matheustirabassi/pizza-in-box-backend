package com.matheustirabassi.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de produto para o usuário administrador.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_product")
public class Produto implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private Double preco;
  private String descricao;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "categoriaId"))
  private List<Categoria> categorias = new ArrayList<>();
  @JsonIgnore
  @OneToMany(mappedBy = "id.produto")
  private Set<ItemPedido> itens = new HashSet<>();

  public Produto(Integer id, String nome, Double preco, String descricao) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
    this.descricao = descricao;
  }

  @JsonIgnore
  public List<Pedido> getPedidos() {
    List<Pedido> lista = new ArrayList<>();
    for (ItemPedido x : itens) {
      lista.add(x.getPedido());
    }
    return lista;
  }

}