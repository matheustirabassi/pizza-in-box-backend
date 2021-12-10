package com.matheustirabassi.cursomc.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Categoria;
import com.matheustirabassi.cursomc.repositories.CategoriaRepository;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.CategoriaService;
import com.matheustirabassi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl extends GenericServiceImpl<Categoria> implements CategoriaService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	 private transient CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Override
	public List<Categoria> findByExample(Categoria example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GenericRepository<Categoria> getDAO() {
		return categoriaRepository;
	}

}
