package com.matheustirabassi.cursomc.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matheustirabassi.cursomc.domain.Login;
import com.matheustirabassi.cursomc.dto.LoginDto;
import com.matheustirabassi.cursomc.services.LoginService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RestController
@RequestMapping(value = "/logins")
public class LoginController {

  @Autowired
  private LoginService service;

  @GetMapping(value = "{id}")
  public ResponseEntity<Login> findById(@PathVariable Integer id) {
    Login obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @Transactional
  @GetMapping
  public ResponseEntity<List<LoginDto>> findAll() {
    List<Login> logins = service.findAllWithCliente();
    return ResponseEntity.ok().body(LoginDto.convertList(logins));
  }

  @PostMapping
  public ResponseEntity<LoginDto> insert(@RequestBody LoginDto obj) {
    obj.setPassword(getPasswordEncoder().encode(obj.getPassword()));
    return ResponseEntity.ok(obj);
  }

  @GetMapping("/passwordValidate")
  public ResponseEntity<?> passwordValidate(@RequestParam String user,
      @RequestParam String password) {

    Optional<Login> obj = Optional.ofNullable(service.findByLogin(user));
    System.out.println(obj);
    if (obj.isEmpty()) {
      log.error("User UNAUTHORIZED!!!");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
    }
    boolean valid = false;
    valid = getPasswordEncoder().matches(password, obj.get().getPassword());

    HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    if (!valid) {
      return ResponseEntity.status(status).body(valid);
    }
    Login login = obj.get();
    return ResponseEntity.status(status).body(login.getCliente().getStatusPermissao());

  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}