package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.Customer;
import com.matheustirabassi.pizzainbox.dto.AddressDto;
import com.matheustirabassi.pizzainbox.dto.CityDto;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.dto.StateDto;
import com.matheustirabassi.pizzainbox.services.CustomerService;
import com.matheustirabassi.pizzainbox.services.exceptions.ObjectNotFoundException;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

  @Autowired
  private CustomerService service;


  @GetMapping(value = "{id}")
  public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
    CustomerDto obj = new CustomerDto(service.findById(id));
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping("/findbyNome")
  public ResponseEntity<?> findByName(@RequestParam String nome) {
    List<Customer> obj = service.findAll();
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<Page<CustomerDto>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAllWithPagination(pageable));
  }

  // TODO: Fazer validações na inserção de usuário
  @PostMapping
  public ResponseEntity<?> insert(@Valid @RequestBody NewCustomerDto newCustomerDto) {
    String clientePassword = newCustomerDto.getLogin().getPassword();
    newCustomerDto.getLogin().setPassword(getPasswordEncoder().encode(clientePassword));
    Customer obj = service.save(newCustomerDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }


  @PostMapping(value = "{id}/addresses")
  public ResponseEntity<?> insertEndereco(@PathVariable Long id,
      @RequestBody AddressDto addressDto) {
    Customer obj = service.insertAddress(id, addressDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "{id}/addresses")
  public ResponseEntity<List<AddressDto>> findAllEnderecos(@PathVariable Long id)
      throws ObjectNotFoundException {
    List<Address> addresses = service.findByAddressesWithCustomerId(id);
    if (addresses.isEmpty()) {
      throw new ObjectNotFoundException("O cliente não tem endereços!");
    }
    List<AddressDto> addressDto = AddressDto.convertList(addresses);
    return ResponseEntity.ok(addressDto);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<?> deleteClienteById(@PathVariable Long id) {
    try {
      service.deleteById(id);
      return ResponseEntity.ok().build();
    } catch (EmptyResultDataAccessException exception) {
      throw new ObjectNotFoundException("Cliente não encontrado!");
    }
  }

  @GetMapping(value = "findByCpf")
  public ResponseEntity<CustomerDto> findByCpf(@RequestParam String cpf)
      throws ObjectNotFoundException {
    return ResponseEntity.ok().body(service.findByDocument(cpf));
  }

  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @PutMapping
  public ResponseEntity<Void> updateCustomer(@RequestBody CustomerDto customerDto) {
    service.updateCustomer(customerDto);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/states")
  public ResponseEntity<List<StateDto>> getStates() {
    return ResponseEntity.ok(service.findAllStates());
  }

  @GetMapping(value = "/cities")
  public ResponseEntity<List<CityDto>> getCities(@RequestParam Long stateId) {
    return ResponseEntity.ok(service.findAllByStateId(stateId));
  }
}