package com.matheustirabassi.pizzainbox.controllers.exceptions;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StandardError implements Serializable {

  private static final long serialVersionUID = 1L;
  private Integer status;
  private String msg;
  private Long timeStamp;

}