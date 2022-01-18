package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Login;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewLoginDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;
  @NotEmpty(message = "Preenchimento obrigatório")
  private String user;
  @NotEmpty(message = "Preenchimento obrigatório")
  private String password;

  public NewLoginDto(Login login) {
    user = login.getUsername();
    password = login.getPassword();
  }

  public static List<NewLoginDto> convertList(List<Login> logins) {
    return logins.stream().map(NewLoginDto::new).collect(Collectors.toList());
  }

}