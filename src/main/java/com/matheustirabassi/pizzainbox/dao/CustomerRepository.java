package com.matheustirabassi.pizzainbox.dao;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer> {

  public Optional<Customer> findByName(String user);

  @Query(value = "select u from Address as u where u.customer.id = :id")
  public Optional<List<Address>> findAddressesByClienteId(@Param("id") Long id);

  public Optional<Customer> findCustomerAddressesByDocument(String document);

  @Override
  @Query(value = "SELECT c FROM Customer as c JOIN FETCH c.addresses WHERE c.id = :id")
  public Optional<Customer> findById(@Param("id") Long id);

  @Query(value = "select case when (count(email) > 0) then true else false end "
      + "from Customer c where c.email = :email")
  public Boolean findByEmailExists(@Param("email") String email);
}