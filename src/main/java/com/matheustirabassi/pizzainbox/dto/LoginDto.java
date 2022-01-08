package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Login;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  private Long id;
  @Column(unique = true)
  private String user;
  private String password;

  public LoginDto(Login login) {
    id = login.getId();
    user = login.getUsername();
    password = login.getPassword();
  }

  public static List<LoginDto> convertList(List<Login> logins) {
    return logins.stream().map(LoginDto::new).collect(Collectors.toList());
  }

}