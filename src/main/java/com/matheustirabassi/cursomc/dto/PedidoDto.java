package com.matheustirabassi.cursomc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.matheustirabassi.cursomc.domain.Endereco;
import com.matheustirabassi.cursomc.domain.ItemPedido;
import com.matheustirabassi.cursomc.domain.Pedido;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private Date instante;
  private Endereco enderecoDeEntrega;
  private Set<ItemPedido> itens = new HashSet<>();

  public PedidoDto(Pedido pedido) {
    this.id = pedido.getId();
    this.instante = pedido.getInstante();
    this.enderecoDeEntrega = pedido.getEnderecoDeEntrega();
    this.itens = pedido.getItens();
  }

  public static List<PedidoDto> convert(List<Pedido> pedidos) {
    return pedidos.stream().map(PedidoDto::new).collect(Collectors.toList());

  }
}