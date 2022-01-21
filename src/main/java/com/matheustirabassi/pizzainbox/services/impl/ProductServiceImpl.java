package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CategoryRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.ProductRepository;
import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.dto.ProductDto;
import com.matheustirabassi.pizzainbox.services.ProductService;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  protected GenericRepository<Product> getDao() {
    return productRepository;
  }

  /**
   * Busca todos os produtos com paginação.
   *
   * @param pageable a paginação.
   * @return a lista de produtos paginada.
   */
  public Page<ProductDto> findAllWithPagination(Pageable pageable) {
    productRepository.findAll();
    Page<Product> result = productRepository.findAll(pageable);
    return result.map(ProductDto::new);
  }

  @Override
  public Product fromDto(ProductDto dto) {
    return Product.builder()
        .name(dto.getName())
        .price(dto.getPrice())
        .description(dto.getDescription())
        .build();
  }

  public Page<Product> searchProducts(String name, List<Long> ids, Pageable pageable) {
    List<Category> categories = categoryRepository.findAllById(ids);
    return productRepository.searchProducts(name, categories, pageable);
  }

  @Transactional
  public void addProductCategory(Long id, List<Long> categoriesIds) {
    log.info("Adicionando produto à categoria...");
    Product product = findById(id);
    List<Category> categoriesList = categoryRepository.findAllById(categoriesIds);
    for (Category category : categoriesList) {
      category.getProducts().add(product);
      product.getCategories().add(category);
    }
  }
}