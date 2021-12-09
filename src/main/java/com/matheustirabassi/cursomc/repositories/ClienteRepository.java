package com.matheustirabassi.cursomc.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.matheustirabassi.cursomc.domain.Cliente;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente> {
	public Optional<Cliente> findByNome(String user);
}
