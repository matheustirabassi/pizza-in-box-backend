package com.matheustirabassi.cursomc.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.dto.ClienteDto;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.services.ClienteService;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente findByNome(String nome) {
		Optional<Cliente> obj = clienteRepository.findByNome(nome);
		return obj.orElse(null);
	}

	@Override
	public List<Cliente> findByExample(Cliente example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<ClienteDto> findAllWithPagination(Pageable pageable) {
		Page<Cliente> result = clienteRepository.findAll(pageable);
		log.info("Buscando todos os clientes por paginação...");
		return result.map(ClienteDto::new);
	}

	@Override
	protected GenericRepository<Cliente> getDAO() {
		return clienteRepository;
	}

	@Override
	public Cliente fromDto(ClienteDto dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), dto.getTipo().getCod(),
				dto.getStatusPermissao().getCod(), dto.getTelefones());
	}

	@Override
	public List<Endereco> findByEnderecosWithClienteId(Integer id) {
		return clienteRepository.findByEnderecosWithClienteId(id);
	}

}
