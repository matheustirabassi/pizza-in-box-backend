package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.CityRepository;
import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.LoginRepository;
import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.City;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.domain.State;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.services.CustomerService;
import com.matheustirabassi.pizzainbox.services.exceptions.DataIntegrityException;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import com.matheustirabassi.pizzainbox.utils.ObjectMapperUtils;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer> implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private LoginRepository loginRepository;

  public CustomerDto findByNome(String nome) {
    Optional<Customer> obj = customerRepository.findByName(nome);
    CustomerDto customerDto = new CustomerDto(
        obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!")));
    return customerDto;
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
  public Customer fromDto(NewCustomerDto dto) {
    Customer customer = ObjectMapperUtils.map(dto, Customer.class);
    customer.getLogin().setCustomer(customer);
    for (Address address : customer.getAddresses()) {
      address.setCustomer(customer);
    }
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
    state.setId(addressDto.getCity());
    City city = new City();
    city.setId(addressDto.getCity());
    state.getCities().add(city);

    return new Address(addressDto.getStreet(), addressDto.getNumber(), addressDto.getComplement(),
        addressDto.getDistrict(), addressDto.getCep(), city);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Address> findByAddressesWithCustomerId(Long id) {
    return customerRepository.findAddressesByClienteId(id).orElseThrow(
        () -> new ObjectNotFoundException("Endereços não encontrados para esse cliente"));
  }

  @Transactional
  @Override
  public Customer insertAddress(Long id, AddressDto addressDto) {
    // TODO: o id do endereço não deve ser necessariamente ser setado
    addressDto.setId(null);
    Customer obj = findById(id);
    Address address = fromEnderecoDto(addressDto);
    address.setCustomer(obj);
    obj.getAddresses().add(address);
    return saveOrUpdate(obj);
  }

  @Transactional(readOnly = true)
  @Override
  public CustomerDto findByDocument(String text) {
    CustomerDto customerDto = new CustomerDto(
        customerRepository.findCustomerAddressesByDocument(text)
            .orElseThrow(() -> new ObjectNotFoundException("Cliente Não encontrado")));
    return customerDto;
  }

  @Transactional
  @Override
  public void updateCustomer(CustomerDto customerDto) {
    if (customerRepository.findByEmailExists(customerDto.getEmail())) {
      throw new DataIntegrityException("Esse email já existe!");
    }
    Customer customerPersistence = findById(customerDto.getId());
    updateData(customerPersistence, ObjectMapperUtils.map(customerDto, Customer.class));
    saveOrUpdate(customerPersistence);
  }

  private void updateData(Customer customer, Customer newCustomer) {
    customer.setName(newCustomer.getName());
    customer.setEmail(newCustomer.getEmail());
  }

  public Customer save(NewCustomerDto dto) {
    
    if (customerRepository.findByEmailExists(dto.getEmail())) {
      throw new DataIntegrityException("Esse email já existe!");
    }
    if (loginRepository.findByUsernameExists(
        dto.getLogin().getUser())) {
      throw new DataIntegrityException("Esse nome de usuário já existe!");
    }
    return saveOrUpdate(fromDto(dto));
  }

}