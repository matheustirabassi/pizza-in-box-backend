package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CategoryRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.services.CategoryService;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category>
    implements CategoryService {

  private static final long serialVersionUID = 1L;

  @Autowired
  private transient CategoryRepository categoryRepository;

  public Category find(Long id) {
    Optional<Category> obj = categoryRepository.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
  }

  @Override
  protected GenericRepository<Category> getDao() {
    return categoryRepository;
  }

}