package com.matheustirabassi.pizzainbox.controllers.exceptions;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FieldMessage implements Serializable {

  private static final long serialVersionUID = 1L;

  private String fieldName;
  private String message;
}