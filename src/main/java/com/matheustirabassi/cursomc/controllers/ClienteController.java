package com.matheustirabassi.cursomc.controllers;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.dto.ClienteDto;
import com.matheustirabassi.cursomc.dto.EnderecoDto;
import com.matheustirabassi.cursomc.services.ClienteService;
import java.net.URI;
import java.util.List;
import javassist.tools.rmi.ObjectNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

  @Autowired
  private ClienteService service;


  @GetMapping(value = "{id}")
  public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
    ClienteDto obj = new ClienteDto(service.findById(id));
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping("/findbyNome")
  public ResponseEntity<?> findByName(@RequestParam String nome) {
    List<Cliente> obj = service.findAll();
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<Page<ClienteDto>> findAll(Pageable pageable) {
    return ResponseEntity.ok(service.findAllWithPagination(pageable));
  }

  @PostMapping
  public ResponseEntity<?> insert(@RequestBody ClienteDto clienteDto) {
    System.out.println(clienteDto.getLogin().getPassword());
    String clientePassword = clienteDto.getLogin().getPassword();
    clienteDto.getLogin().setPassword(getPasswordEncoder().encode(clientePassword));

    Cliente obj = service.saveOrUpdate(service.fromDto(clienteDto));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

  @Transactional
  @PostMapping(value = "{id}/enderecos")
  public ResponseEntity<?> insertEndereco(@PathVariable Integer id,
      @RequestBody EnderecoDto enderecoDto) {
    Cliente obj = service.insertEnderecoCliente(id, enderecoDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping(value = "{id}/enderecos")
  public ResponseEntity<List<EnderecoDto>> findAllEnderecos(@PathVariable Integer id) {
    List<Endereco> enderecos = service.findByEnderecosWithClienteId(id);
    List<EnderecoDto> enderecoDto = EnderecoDto.convertList(enderecos);
    return ResponseEntity.ok(enderecoDto);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<?> deleteClienteById(@PathVariable Integer id) {
    service.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "findByCpf")
  public ResponseEntity<ClienteDto> findByCpf(@RequestParam String cpf)
      throws ObjectNotFoundException {
    ClienteDto dto = new ClienteDto(service.findByCpfOuCnpj(cpf));
    return ResponseEntity.ok().body(dto);
  }

  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}