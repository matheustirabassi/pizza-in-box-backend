package com.matheustirabassi.cursomc.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.matheustirabassi.cursomc.domain.Login;

@Repository
public interface LoginRepository extends GenericRepository<Login> {
	@Query(value = "select u from Login u JOIN FETCH u.cliente v where u.user = :user")
	public Optional<Login> findByUser(String user);
}
