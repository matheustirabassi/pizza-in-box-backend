package com.matheustirabassi.cursomc.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.matheustirabassi.cursomc.domain.Endereco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDto {
  private Integer id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;
  private String cidade;
  private String estado;

  public EnderecoDto(Endereco endereco) {
    this.id = endereco.getId();
    this.logradouro = endereco.getLogradouro();
    this.numero = endereco.getNumero();
    this.complemento = endereco.getComplemento();
    this.bairro = endereco.getBairro();
    this.cep = endereco.getCep();
    this.cidade = endereco.getCidade().getNome();
    this.estado = endereco.getCidade().getEstado().getNome();
  }

  public static List<EnderecoDto> convertList(List<Endereco> enderecos) {
    return enderecos.stream().map(EnderecoDto::new).collect(Collectors.toList());
  }
}