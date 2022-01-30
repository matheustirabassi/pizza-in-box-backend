package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.State;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.CityDto;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.dto.StateDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService extends GenericService<Customer> {

  Customer fromDto(NewCustomerDto dto);

  Page<CustomerDto> findAllWithPagination(Pageable pageable);

  List<Address> findByAddressesWithCustomerId(Long id);

  Customer insertAddress(Long id, AddressDto addressDto);

  CustomerDto findByDocument(String text);

  void updateCustomer(CustomerDto customerDto);

  Customer save(NewCustomerDto dto);

  List<StateDto> findAllStates();

  List<CityDto> findAllByStateId(Long id);
}