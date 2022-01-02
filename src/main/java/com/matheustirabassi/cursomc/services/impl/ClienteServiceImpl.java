package com.matheustirabassi.cursomc.services.impl;

import com.matheustirabassi.cursomc.domain.Cidade;
import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.domain.Estado;
import com.matheustirabassi.cursomc.dto.ClienteDto;
import com.matheustirabassi.cursomc.dto.EnderecoDto;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.ClienteService;
import com.matheustirabassi.cursomc.services.exceptions.DataIntegrityException;
import com.matheustirabassi.cursomc.services.exceptions.ObjectNotFoundException;
import com.matheustirabassi.cursomc.utils.ObjectMapperUtils;
import java.util.ArrayList;
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
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService {

  private static final long serialVersionUID = 1L;

  @Autowired
  private ClienteRepository clienteRepository;

  @Override
  public Cliente saveOrUpdate(Cliente cliente) {
    try {
      return clienteRepository.save(cliente);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException("O username já existe!");
    }
  }

  public Cliente findById(Integer id) {
    Optional<Cliente> obj = getDAO().findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
  }

  public Cliente findByNome(String nome) {
    Optional<Cliente> obj = clienteRepository.findByNome(nome);
    return obj.orElse(null);
  }

  @Override
  public List<Cliente> findByExample(Cliente example, Integer start, Integer limit) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * Faz a busca paginada de clientes.
   *
   * @param pageable a paginação.
   * @return os clientes paginados.
   */
  @Transactional(readOnly = true)
  public Page<ClienteDto> findAllWithPagination(Pageable pageable) {
    Page<Cliente> result = getDAO().findAll(pageable);
    log.info("Buscando todos os clientes por paginação...");
    return result.map(ClienteDto::new);
  }

  @Override
  protected GenericRepository<Cliente> getDAO() {
    return clienteRepository;
  }

  /**
   * Converte ClienteDto para Cliente.
   *
   * @param dto o ClienteDto.
   * @return o Cliente.
   */
  @Override
  public Cliente fromDto(ClienteDto dto) {
    Cliente cliente = ObjectMapperUtils.map(dto, Cliente.class);
    cliente.getLogin().setCliente(cliente);
    cliente.getLogin().setUsername(dto.getLogin().getUser());
    Estado estado = new Estado();
    estado.setNome(dto.getEnderecos().get(0).getEstado());

    Cidade cidade = new Cidade(null, dto.getEnderecos().get(0).getCidade(), estado);
    estado.getCidades().add(cidade);
    Endereco endereco = ObjectMapperUtils.map(dto.getEnderecos().get(0), Endereco.class);
    endereco.setCidade(cidade);
    endereco.setCliente(cliente);

    cliente.setEnderecos(List.of(endereco));

    return cliente;
  }

  /**
   * Converte EndereçoDto para Endereço.
   *
   * @param enderecoDto o endereçoDto.
   * @return o Endereço
   */
  public Endereco fromEnderecoDto(EnderecoDto enderecoDto) {
    Cidade cidade = new Cidade(null, enderecoDto.getCidade(), null);
    Estado estado = new Estado(new ArrayList<>(), null, enderecoDto.getEstado());
    cidade.setEstado(estado);
    estado.getCidades().add(cidade);
    return new Endereco(null, enderecoDto.getLogradouro(), enderecoDto.getNumero(),
        enderecoDto.getComplemento(), enderecoDto.getBairro(), enderecoDto.getCep(), cidade, null);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Endereco> findByEnderecosWithClienteId(Integer id) {
    return clienteRepository.findByEnderecosWithClienteId(id).orElseThrow(
        () -> new ObjectNotFoundException("Endereços não encontrados para esse cliente"));
  }

  @Transactional
  @Override
  public Cliente insertEnderecoCliente(Integer id, EnderecoDto enderecoDto) {
    Cliente obj = findById(id);
    Endereco endereco = fromEnderecoDto(enderecoDto);
    endereco.setCliente(obj);
    obj.getEnderecos().add(endereco);
    return saveOrUpdate(obj);
  }

  @Transactional
  @Override
  public Cliente findByCpfOuCnpj(String text) throws ObjectNotFoundException {
    try {
      return clienteRepository.findByCpfOuCnpj(text).get(0);
    } catch (IndexOutOfBoundsException exception) {
      throw new ObjectNotFoundException("Cliente Não encontrado");
    }
  }

}