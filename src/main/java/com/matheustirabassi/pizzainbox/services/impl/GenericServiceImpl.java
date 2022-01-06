package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.services.GenericService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

  @Autowired
  protected abstract GenericRepository<T> getDAO();

  @Override
  @Transactional
  public void delete(T t) {
    getDAO().delete(t);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    getDAO().deleteById(id);
  }

  @Override
  @Transactional
  public List<T> findAll() {
    return getDAO().findAll();
  }

  @Override
  @Transactional
  public T findById(Long id) {
    Optional<T> obj = getDAO().findById(id);
    return obj.orElse(null);

  }

  @Override
  @Transactional
  public T saveOrUpdate(T t) {
    return getDAO().save(t);
  }

  /**
   * Faz a busca paginada de clientes.
   *
   * @param pageable a paginação.
   * @return os clientes paginados.
   */
  @Transactional(readOnly = true)
  public Page<T> findWithPagination(Pageable pageable) {
    return getDAO().findAll(pageable);
  }
}