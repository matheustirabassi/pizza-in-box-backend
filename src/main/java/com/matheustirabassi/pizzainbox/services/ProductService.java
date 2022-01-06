package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.dto.ProductDto;

public interface ProductService extends GenericService<Product> {

  public Product fromDto(ProductDto dto);

}