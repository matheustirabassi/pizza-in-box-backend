package com.matheustirabassi.pizzainbox.services.exceptions;

/**
 * Classe de exceções para camada de serviço.
 */
public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ServiceException(String msg) {
    super(msg);
  }

  public ServiceException(String msg, Throwable cause) {
    super(msg, cause);
  }

}