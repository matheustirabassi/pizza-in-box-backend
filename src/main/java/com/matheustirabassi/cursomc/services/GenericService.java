package com.matheustirabassi.cursomc.services;

import java.util.List;

public interface GenericService<T> {

	public T saveOrUpdate(T entity);

	public void delete(T entity);

	public T findById(Integer id);

	public List<T> findByExample(T example, Integer start, Integer limit);

	public List<T> findAll();
}