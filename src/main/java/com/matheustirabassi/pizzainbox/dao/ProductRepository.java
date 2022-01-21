package com.matheustirabassi.pizzainbox.dao;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.domain.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product> {

  @Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories c WHERE p.name LIKE %:name% AND "
      + "c IN :categories")
  Page<Product> searchProducts(String name, List<Category> categories, Pageable pageable);

}