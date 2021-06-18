package com.matheustirabassi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Categoria;
import com.matheustirabassi.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Integer id) {
		Optional <Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
		
		
	}
}
