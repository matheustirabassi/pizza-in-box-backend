package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.dto.CategoryDto;

public interface CategoryService extends GenericService<Category> {

  Category fromDTO(CategoryDto dto);
}