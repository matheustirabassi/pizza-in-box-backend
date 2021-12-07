package com.matheustirabassi.cursomc.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Categoria;
import com.matheustirabassi.cursomc.repositories.CategoriaRepository;
import com.matheustirabassi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
