package com.matheustirabassi.pizzainbox.dao;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.domain.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product> {

  Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories,
      Pageable pageable);

}