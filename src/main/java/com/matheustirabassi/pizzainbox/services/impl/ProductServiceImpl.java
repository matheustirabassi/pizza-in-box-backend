package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.ProductRepository;
import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.dto.ProductDto;
import com.matheustirabassi.pizzainbox.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  protected GenericRepository<Product> getDAO() {
    return productRepository;
  }

  public Page<ProductDto> findAllWithPagination(Pageable pageable) {
    productRepository.findAll();
    Page<Product> result = productRepository.findAll(pageable);
    return result.map(ProductDto::new);
  }

  @Override
  public Product fromDto(ProductDto dto) {
    Product product = Product.builder()
        .name(dto.getName())
        .price(dto.getPrice())
        .description(dto.getDescription())
        .build();
    return product;
  }

}