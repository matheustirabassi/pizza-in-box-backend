package com.matheustirabassi.pizzainbox.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenericService<T> {

  T save(T entity);

  T update(T entity, Long id);

  void delete(T entity);

  T findById(Long id);

  Page<T> findWithPagination(Pageable pageable);

  List<T> findAll();

  void deleteById(Long id);
}