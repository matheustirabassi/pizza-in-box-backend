package com.matheustirabassi.cursomc.repositories;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente>,
    JpaRepository<Cliente, Integer> {

  public Optional<Cliente> findByNome(String user);

  @Query(value = "select u from Endereco as u where u.cliente.id = :id")
  public Optional<List<Endereco>> findByEnderecosWithClienteId(@Param("id") Integer id);

  public List<Cliente> findByCpfOuCnpj(String cpfOuCnpj);

  @Override
  @Query(value = "SELECT c FROM Cliente as c JOIN FETCH c.enderecos WHERE c.id = :id")
  public Optional<Cliente> findById(@Param("id") Integer id);
}