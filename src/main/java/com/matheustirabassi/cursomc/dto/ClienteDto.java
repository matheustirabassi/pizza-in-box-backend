package com.matheustirabassi.cursomc.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.matheustirabassi.cursomc.domain.Cliente;
import com.matheustirabassi.cursomc.domain.enums.StatusPermissao;
import com.matheustirabassi.cursomc.domain.enums.TipoCliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto {
  private Integer id;
  private String nome;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;
  private Integer statusPermissao;
  private Set<String> telefones = new HashSet<>();

  public ClienteDto(Cliente cliente) {
    this.id = cliente.getId();
    this.nome = cliente.getNome();
    this.email = cliente.getEmail();
    this.cpfOuCnpj = cliente.getCpfOuCnpj();
    this.tipo = cliente.getTipo().getCod();
    this.statusPermissao = cliente.getStatusPermissao().getCod();
    this.telefones = cliente.getTelefones();
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