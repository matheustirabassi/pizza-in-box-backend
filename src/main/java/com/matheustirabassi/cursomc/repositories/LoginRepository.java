package com.matheustirabassi.cursomc.repositories;

import com.matheustirabassi.cursomc.domain.Login;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends GenericRepository<Login> {

  @Query(value = "select u from Login u JOIN FETCH u.cliente v where u.username = :user")
  public Optional<Login> findByUser(String user);


  @Query(value = "select u from Login u JOIN FETCH u.cliente v")
  public List<Login> findAllWithCliente();
}