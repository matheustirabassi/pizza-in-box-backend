package com.matheustirabassi.pizzainbox.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<T> {

  public T saveOrUpdate(T entity);

  public void delete(T entity);

  public T findById(Long id);

  public Page<T> findWithPagination(Pageable pageable);

  public List<T> findAll();

  void deleteById(Long id);
}