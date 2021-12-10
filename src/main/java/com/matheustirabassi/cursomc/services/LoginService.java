package com.matheustirabassi.cursomc.services;

import com.matheustirabassi.cursomc.domain.Login;

public interface LoginService extends GenericService<Login>{
	public Login findByLogin(String user);
}
