package com.matheustirabassi.cursomc.services;

import com.matheustirabassi.cursomc.domain.Produto;
import com.matheustirabassi.cursomc.dto.ProdutoDto;

public interface ProdutoService extends GenericService<Produto> {
	public Produto fromDto(ProdutoDto dto);
		
}

