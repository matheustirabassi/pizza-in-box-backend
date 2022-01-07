package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.dto.OrderDto;
import com.matheustirabassi.pizzainbox.services.impl.OrderServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class OrderController {

  @Autowired
  private OrderServiceImpl service;

  @GetMapping(value = "{id}")
  public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
    OrderDto obj = new OrderDto(service.findById(id));
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<OrderDto>> findAll() {
    List<Order> orders = service.findAll();
    return ResponseEntity.ok().body(OrderDto.convert(orders));
  }

  @PostMapping
  public ResponseEntity<OrderDto> insert(@RequestBody Order order) {
    OrderDto orderDto = new OrderDto(service.save(order));
    return ResponseEntity.ok(orderDto);
  }
}