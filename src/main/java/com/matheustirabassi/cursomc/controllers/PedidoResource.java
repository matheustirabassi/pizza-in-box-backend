package com.matheustirabassi.cursomc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matheustirabassi.cursomc.domain.Pedido;
import com.matheustirabassi.cursomc.dto.PedidoDto;
import com.matheustirabassi.cursomc.services.impl.PedidoServiceImpl;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  private PedidoServiceImpl service;

  @GetMapping(value = "{id}")
  public ResponseEntity<PedidoDto> findById(@PathVariable Integer id) {
    PedidoDto obj = new PedidoDto(service.findById(id));
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<PedidoDto>> findAll() {
    List<Pedido> pedidos = service.findAll();
    return ResponseEntity.ok().body(PedidoDto.convert(pedidos));
  }

  @PostMapping
  public ResponseEntity<PedidoDto> insert(@RequestBody Pedido pedido) {
    PedidoDto pedidoDto = new PedidoDto(service.saveOrUpdate(pedido));
    return ResponseEntity.ok(pedidoDto);
  }
}