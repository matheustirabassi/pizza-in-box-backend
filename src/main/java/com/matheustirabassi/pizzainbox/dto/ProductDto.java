package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Product;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private Double price;
  private String description;

  public ProductDto(Product product) {
    this.id = product.getId();
    this.name = product.getName();
    this.price = product.getPrice();
    this.description = product.getDescription();
  }

  public static List<ProductDto> convertList(List<Product> products) {
    return products.stream().map(ProductDto::new).collect(Collectors.toList());

  }

}