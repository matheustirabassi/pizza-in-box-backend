package com.matheustirabassi.cursomc.dto;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.enums.StatusPermissao;
import com.matheustirabassi.cursomc.domain.enums.TipoCliente;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ClienteDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id;
  private String nome;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;
  private Integer statusPermissao;
  private Set<String> telefones;
  private List<EnderecoDto> enderecos;
  private LoginDto login;

  public ClienteDto(Cliente cliente) {
    this.id = cliente.getId();
    this.nome = cliente.getNome();
    this.email = cliente.getEmail();
    this.cpfOuCnpj = cliente.getCpfOuCnpj();
    this.setTipo(cliente.getTipo());
    this.setStatusPermissao(cliente.getStatusPermissao());
    this.telefones = cliente.getTelefones();
    this.enderecos = EnderecoDto.convertList(cliente.getEnderecos());
    this.login = new LoginDto(cliente.getLogin());
  }

  public static List<ClienteDto> convertList(List<Cliente> clientes) {
    return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
  }

  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public StatusPermissao getStatusPermissao() {
    return StatusPermissao.toEnum(statusPermissao);
  }

  public void setStatusPermissao(StatusPermissao statusPermissao) {
    this.statusPermissao = statusPermissao.getCod();
  }

}