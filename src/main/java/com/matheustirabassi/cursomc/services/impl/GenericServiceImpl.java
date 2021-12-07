package com.matheustirabassi.cursomc.services.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.GenericService;

public abstract class GenericServiceImpl<T> implements GenericService<T>, Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	protected abstract GenericRepository<T> getDAO();

	@Override
	public void delete(T t) {
		getDAO().delete(t);
	}

	@Override
	public List<T> findAll() {
		return getDAO().findAll();
	}

	@Override
	public T findById(Integer id) {
		Optional<T> obj = getDAO().findById(id);
		return obj.get();
	
	}

	@Override
	public T saveOrUpdate(T t) {
		return getDAO().save(t);
	}
}
