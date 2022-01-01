package com.matheustirabassi.cursomc.dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.matheustirabassi.cursomc.domain.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String nome;
  private Double preco;
  private String descricao;

  public ProdutoDto(Produto produto) {
    this.id = produto.getId();
    this.nome = produto.getNome();
    this.preco = produto.getPreco();
    this.descricao = produto.getDescricao();
  }

  public static List<ProdutoDto> convertList(List<Produto> produtos) {
    return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());

  }

}