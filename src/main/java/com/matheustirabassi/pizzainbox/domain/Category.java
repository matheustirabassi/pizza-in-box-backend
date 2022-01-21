package com.matheustirabassi.pizzainbox.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe de categoria de produtos.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_category")
public class Category extends BaseEntity {

  private String name;

  @ManyToMany(mappedBy = "categories", cascade = CascadeType.PERSIST)
  private List<Product> products = new ArrayList<>();

}