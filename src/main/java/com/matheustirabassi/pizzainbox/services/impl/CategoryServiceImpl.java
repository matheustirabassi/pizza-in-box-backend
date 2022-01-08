package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CategoryRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.dto.CategoryDto;
import com.matheustirabassi.pizzainbox.services.CategoryService;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import com.matheustirabassi.pizzainbox.utils.ObjectMapperUtils;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
  public Category fromDTO(CategoryDto dto) {
    return ObjectMapperUtils.map(dto, Category.class);
  }

  @Transactional
  @Override
  public void updateCategory(CategoryDto categoryDto) {
    Category categoryPersistence = findById(categoryDto.getId());
    updateData(categoryPersistence, ObjectMapperUtils.map(categoryDto, Category.class));
    saveOrUpdate(categoryPersistence);
  }

  private void updateData(Category category, Category newCategory) {
    category.setName(newCategory.getName());
  }
}