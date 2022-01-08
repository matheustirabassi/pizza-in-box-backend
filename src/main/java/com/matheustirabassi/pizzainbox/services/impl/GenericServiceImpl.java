package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.services.GenericService;
import com.matheustirabassi.pizzainbox.services.exceptions.DataIntegrityException;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<T> implements GenericService<T> {

  @Autowired
  protected abstract GenericRepository<T> getDao();

  @Override
  @Transactional
  public void delete(T t) {
    getDao().delete(t);
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    findById(id);
    try {
      getDao().deleteById(id);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException(
          String.format("Não é possível excluir uma %s que tenha coleçòes associadas.",
              ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
                  .getActualTypeArguments()[0]).getSimpleName()));
    }
  }

  @Override
  @Transactional
  public List<T> findAll() {
    return getDao().findAll();
  }

  @Override
  @Transactional
  public T findById(Long id) {
    Optional<T> obj = getDao().findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException(
        String.format("Objeto não encontrado! Id: %d, Tipo: %s", id,
            ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0]).getSimpleName())));
  }

  @Override
  @Transactional
  public T saveOrUpdate(T entity) {
    return getDao().save(entity);
  }

  /**
   * Faz a busca paginada de clientes.
   *
   * @param pageable a paginação.
   * @return os clientes paginados.
   */
  @Override
  @Transactional(readOnly = true)
  public Page<T> findWithPagination(Pageable pageable) {
    return getDao().findAll(pageable);
  }
}