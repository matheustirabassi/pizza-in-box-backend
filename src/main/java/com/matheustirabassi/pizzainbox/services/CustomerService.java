package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends GenericService<Customer> {

  public Customer fromDto(CustomerDto dto);

  CustomerDto saveOrUpdate(CustomerDto customer);

  public Page<CustomerDto> findAllWithPagination(Pageable pageable);

  public List<Address> findByAddressesWithCustomerId(Long id);

  public CustomerDto insertAddress(Long id, AddressDto addressDto);

  public CustomerDto findByDocument(String text);
}