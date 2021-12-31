package com.matheustirabassi.cursomc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente> {
  public Optional<Cliente> findByNome(String user);

  @Query(value = "select u from Endereco as u where u.cliente.id = :id")
  public List<Endereco> findByEnderecosWithClienteId(@Param("id") Integer id);

  public List<Cliente> findByCpfOuCnpj(String cpfOuCnpj);
}