package com.matheustirabassi.pizzainbox.dao;

import com.matheustirabassi.pizzainbox.domain.Login;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends GenericRepository<Login> {

  @Query(value = "select u from Login u JOIN FETCH u.customer v where u.username = :user")
  public Optional<Login> findByUser(String user);


  @Query(value = "select u from Login u JOIN FETCH u.customer v")
  public List<Login> findAllWithCliente();

  @Query(value = "select case when (count(username) > 0) then true else false end "
      + "from Login l where l.username = :username")
  public Boolean findByUsernameExists(@Param("username") String username);
}