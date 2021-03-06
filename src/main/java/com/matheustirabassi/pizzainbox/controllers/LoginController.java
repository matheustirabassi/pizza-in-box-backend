package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.dto.NewLoginDto;
import com.matheustirabassi.pizzainbox.services.LoginService;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
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

@Component
@Log4j2
@RestController
@RequestMapping(value = "/logins")
public class LoginController {

  @Autowired
  private LoginService service;

  @GetMapping(value = "{id}")
  public ResponseEntity<Login> findById(@PathVariable Long id) {
    Login obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }


  @GetMapping
  public ResponseEntity<List<NewLoginDto>> findAll() {
    List<NewLoginDto> logins = service.findAllWithCliente();
    return ResponseEntity.ok().body(logins);
  }

  @PostMapping
  public ResponseEntity<NewLoginDto> insert(@RequestBody NewLoginDto obj) {
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
    boolean valid;
    valid = getPasswordEncoder().matches(password, obj.get().getPassword());

    HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
    if (!valid) {
      return ResponseEntity.status(status).body(false);
    }
    Login login = obj.get();
    return ResponseEntity.status(status).body(login.getCustomer().getPermissionStatus());

  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }
}