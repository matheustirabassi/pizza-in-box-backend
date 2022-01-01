package com.matheustirabassi.cursomc.services.impl;

import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.GenericService;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<T> implements GenericService<T>, Serializable {

  private static final long serialVersionUID = 1L;

  @Autowired
  protected abstract GenericRepository<T> getDAO();

  @Override
  @Transactional
  public void delete(T t) {
    getDAO().delete(t);
  }

  @Override
  @Transactional
  public void deleteById(Integer id) {
    getDAO().deleteById(id);
  }

  @Override
  @Transactional
  public List<T> findAll() {
    return getDAO().findAll();
  }

  @Override
  @Transactional
  public T findById(Integer id) {
    Optional<T> obj = getDAO().findById(id);
    return obj.orElse(null);

  }

  @Override
  @Transactional
  public T saveOrUpdate(T t) {
    return getDAO().save(t);
  }
}