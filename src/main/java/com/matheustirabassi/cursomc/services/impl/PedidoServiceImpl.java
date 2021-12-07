package com.matheustirabassi.cursomc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Pedido;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.repositories.PedidoRepository;
import com.matheustirabassi.cursomc.services.PedidoService;

@Service
public class PedidoServiceImpl extends GenericServiceImpl<Pedido> implements PedidoService{

	private static final long serialVersionUID = 1L;
	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public List<Pedido> findByExample(Pedido example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected GenericRepository<Pedido> getDAO() {
		return (GenericRepository<Pedido>) pedidoRepository;
	}

}
