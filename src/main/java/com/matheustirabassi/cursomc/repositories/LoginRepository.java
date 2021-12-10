package com.matheustirabassi.cursomc.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.matheustirabassi.cursomc.domain.Login;

@Repository
public interface LoginRepository extends GenericRepository<Login> {
	public Optional<Login> findByUser(String user);
}
