package com.matheustirabassi.cursomc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Produto;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.repositories.ProdutoRepository;
import com.matheustirabassi.cursomc.services.ProdutoService;

@Service
public class ProdutoServiceImpl extends GenericServiceImpl<Produto> implements ProdutoService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findByExample(Produto example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	protected GenericRepository<Produto> getDAO() {
		return produtoRepository;
	}

	
}