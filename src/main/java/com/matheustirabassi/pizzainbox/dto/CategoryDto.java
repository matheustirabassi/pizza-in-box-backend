package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Category;
import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class CategoryDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;

  @NotEmpty(message = "Preenchimento obrigatório")
  @Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
  private String name;

  public CategoryDto(Category category) {
    this.id = category.getId();
    this.name = category.getName();
  }
}