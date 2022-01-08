package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe de produto para o usuário administrador.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_product")
public class Product extends BaseEntity {

  private String name;
  private Double price;
  private String description;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;
  @JsonIgnore
  @OneToMany(mappedBy = "id.product")
  private Set<OrderItem> items;

  @JsonIgnore
  public Set<Order> getOrders() {
    Set<Order> set = new HashSet<>();
    for (OrderItem x : items) {
      set.add(x.getOrder());
    }
    return set;
  }

}