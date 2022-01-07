package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.State;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import com.matheustirabassi.pizzainbox.services.CustomerService;
import com.matheustirabassi.pizzainbox.services.exceptions.DataIntegrityException;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import com.matheustirabassi.pizzainbox.utils.ObjectMapperUtils;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Transactional
  @Override
  public CustomerDto saveOrUpdate(CustomerDto customer) {
    try {
      Customer customer1 = getDao().save(fromDto(customer));
      return new CustomerDto(customer1);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException(exception.getMessage(), exception);
    }
  }

  public Customer findByNome(String nome) {
    Optional<Customer> obj = customerRepository.findByName(nome);
    return obj.orElse(null);
  }

  /**
   * Faz a busca paginada de clientes.
   *
   * @param pageable a paginação.
   * @return os clientes paginados.
   */
  @Override
  @Transactional(readOnly = true)
  public Page<CustomerDto> findAllWithPagination(Pageable pageable) {
    Page<Customer> result = getDao().findAll(pageable);
    log.info("Buscando todos os clientes por paginação...");
    return result.map(CustomerDto::new);
  }

  @Override
  protected GenericRepository<Customer> getDao() {
    return customerRepository;
  }

  /**
   * Converte ClienteDto para Cliente.
   *
   * @param dto o ClienteDto.
   * @return o Cliente.
   */
  @Override
  public Customer fromDto(CustomerDto dto) {
    Customer customer = ObjectMapperUtils.map(dto, Customer.class);
    customer.getLogin().setCustomer(customer);
    customer.getLogin().setUsername(dto.getLogin().getUser());
    State state = new State();
    state.setName(dto.getAddresses().get(0).getState());

    City city = new City(dto.getAddresses().get(0).getCity(), state);
    state.getCities().add(city);
    Address address = ObjectMapperUtils.map(dto.getAddresses().get(0), Address.class);
    address.setCity(city);
    address.setCustomer(customer);

    customer.setAddresses(List.of(address));

    return customer;
  }

  /**
   * Converte EndereçoDto para Endereço.
   *
   * @param addressDto o endereçoDto.
   * @return o Endereço
   */
  public Address fromEnderecoDto(AddressDto addressDto) {
    State state = new State();
    state.setName(addressDto.getState());
    City city = new City(addressDto.getCity(), state);
    state.getCities().add(city);

    return new Address(addressDto.getStreet(), addressDto.getNumber(),
        addressDto.getComplement(), addressDto.getDistrict(), addressDto.getCep(), city);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Address> findByAddressesWithCustomerId(Long id) {
    return customerRepository.findAddressesByClienteId(id).orElseThrow(
        () -> new ObjectNotFoundException("Endereços não encontrados para esse cliente"));
  }

  @Transactional
  @Override
  public CustomerDto insertAddress(Long id, AddressDto addressDto) {
    Customer obj = findById(id);
    Address address = fromEnderecoDto(addressDto);
    address.setCustomer(obj);
    obj.getAddresses().add(address);
    return new CustomerDto(save(obj));
  }

  @Transactional(readOnly = true)
  @Override
  public CustomerDto findByDocument(String text) {
    CustomerDto customerDto = new CustomerDto(
        customerRepository.findCustomerAddressesByDocument(text)
            .orElseThrow(() -> new ObjectNotFoundException("Cliente Não encontrado")));
    return customerDto;
  }

}