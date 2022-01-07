package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Category;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;

  public CategoryDto(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }
}