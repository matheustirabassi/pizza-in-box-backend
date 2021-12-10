package com.matheustirabassi.cursomc.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheustirabassi.cursomc.domain.Login;
import com.matheustirabassi.cursomc.repositories.GenericRepository;
import com.matheustirabassi.cursomc.repositories.LoginRepository;
import com.matheustirabassi.cursomc.services.LoginService;

@Service
public class LoginServiceImpl extends GenericServiceImpl<Login> implements LoginService {

	private static final long serialVersionUID = 1L;
	@Autowired
	transient LoginRepository loginRepository;

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

	@Override
	protected GenericRepository<Login> getDAO() {
		return loginRepository;
	}

}
