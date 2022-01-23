package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.dto.ProductDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService extends GenericService<Product> {

  Product fromDto(ProductDto dto);

  Page<Product> searchProducts(String name, List<Long> ids, Pageable pageable);

  void addProductCategory(Long id, List<Long> categoriesIds);

}