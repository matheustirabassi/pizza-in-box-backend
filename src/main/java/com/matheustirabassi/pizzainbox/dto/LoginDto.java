package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Login;
import com.matheustirabassi.pizzainbox.domain.enums.PermissionStatus;
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

  private static final long serialVersionUID = 1L;
  private Long id;
  @Column(unique = true)
  private String user;
  private String password;
  private Integer permissionStatus;

  public LoginDto(Login login) {
    id = login.getId();
    user = login.getUsername();
    password = login.getPassword();
    permissionStatus = login.getCustomer().getPermissionStatus().getCod();
  }

  public static List<LoginDto> convertList(List<Login> logins) {
    return logins.stream().map(LoginDto::new).collect(Collectors.toList());
  }

  public PermissionStatus getPermissionStatus() {
    return PermissionStatus.toEnum(permissionStatus);
  }

  public void setPermissionStatus(PermissionStatus permissionStatus) {
    this.permissionStatus = permissionStatus.getCod();
  }
}