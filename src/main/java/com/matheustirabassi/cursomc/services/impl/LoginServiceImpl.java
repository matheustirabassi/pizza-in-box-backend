package com.matheustirabassi.cursomc.services.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.Login;
import com.matheustirabassi.cursomc.dto.LoginDto;
import com.matheustirabassi.cursomc.repositories.ClienteRepository;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.repositories.LoginRepository;
import com.matheustirabassi.cursomc.services.LoginService;
import com.matheustirabassi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class LoginServiceImpl extends GenericServiceImpl<Login> implements LoginService {

	private static final long serialVersionUID = 1L;
	@Autowired
	transient LoginRepository loginRepository;
	@Autowired
	transient ClienteRepository clienteRepository;
	@Override
	public List<Login> findByExample(Login example, Integer start, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login findByLogin(String user) {
		Optional<Login> obj = loginRepository.findByUser(user);
		return obj.orElse(null);
	}

	@Override
	public Login saveOrUpdate(Login login) {
		return loginRepository.save(login);
	}
	public LoginDto saveLoginWithClienteId(LoginDto dto, Integer clienteId) {
		try {
		Login login = fromDto(dto);
		Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
		cliente.setLogin(login);
		cliente.setStatusPermissao(dto.getStatusPermissao());
		login.setCliente(cliente);
		saveOrUpdate(login);
		clienteRepository.save(cliente);
		return new LoginDto(login);
		}
		catch(Exception e) {
			throw new ObjectNotFoundException("Cliente não encontrado ou login duplicado!");
		}
	}
	@Override
	protected GenericRepository<Login> getDAO() {
		return loginRepository;
	}

	@Override
	public Login fromDto(LoginDto dto) {
		Login login = new Login();
		login.setId(dto.getId());
		login.setUser(dto.getUser());
		login.setPassword(dto.getPassword());
		return login;
		
	}

	@Override
	public List<Login> findAllWithCliente() {
		return loginRepository.findAllWithCliente();
	}
}
