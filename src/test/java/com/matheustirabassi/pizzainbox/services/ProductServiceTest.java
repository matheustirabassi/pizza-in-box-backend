package com.matheustirabassi.pizzainbox.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.matheustirabassi.pizzainbox.dao.CategoryRepository;
import com.matheustirabassi.pizzainbox.dao.ProductRepository;
import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.services.impl.ProductServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ProductServiceTest {

  @Mock
  ProductRepository productRepository;

  @Mock
  CategoryRepository categoryRepository;

  @InjectMocks
  ProductServiceImpl productService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void searchProductsTest_AllValid_Sucess() {
    List<Long> categories = Arrays.asList(1L, 2L);

    String productName = "pi";
    Product product1 = new Product();
    product1.setName("pizza");

    Page<Product> productPage = new PageImpl<>(List.of(product1));
    Pageable pageable = PageRequest.of(0, 8);
    when(productService.searchProducts(productName, categories, pageable)).thenReturn(productPage);

    assertEquals(productPage, productService.searchProducts(productName, categories, pageable));
  }
}