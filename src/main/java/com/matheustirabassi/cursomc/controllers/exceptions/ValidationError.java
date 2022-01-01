package com.matheustirabassi.cursomc.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;


public class ValidationError extends StandardError {
  private static final long serialVersionUID = 1L;

  private List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(Integer status, String msg, long timeStamp) {
    super(status, msg, timeStamp);
  }

  /**
   * O nome do método será convertido para nome de campo no objeto Json (porém, sem o get)
   */
  public List<FieldMessage> getErrors() {
    return errors;
  }

  public void addError(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }
}