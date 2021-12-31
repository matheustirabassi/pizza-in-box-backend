package com.matheustirabassi.cursomc.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.dto.ClienteDto;
import com.matheustirabassi.cursomc.dto.EnderecoDto;

public interface ClienteService extends GenericService<Cliente> {
  public Cliente fromDto(ClienteDto dto);

  public Page<ClienteDto> findAllWithPagination(Pageable pageable);

  public List<Endereco> findByEnderecosWithClienteId(Integer id);

  public Cliente insertEnderecoCliente(Integer id, EnderecoDto endereco);

  public Cliente findByCpfOuCnpj(String text);
}