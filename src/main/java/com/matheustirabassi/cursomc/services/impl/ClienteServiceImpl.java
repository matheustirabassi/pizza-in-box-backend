package com.matheustirabassi.cursomc.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.ClienteService;
import com.matheustirabassi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	@Override
	public List<Cliente> findByExample(Cliente example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	protected GenericRepository<Cliente> getDAO() {
		return clienteRepository;
	}

}
