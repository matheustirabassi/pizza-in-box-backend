package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.dto.OrderDto;
import com.matheustirabassi.pizzainbox.services.OrderService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  @Autowired
  private OrderService service;

  @GetMapping(value = "{id}")
  public ResponseEntity<OrderDto> findById(@PathVariable Long id) {
    return ResponseEntity.ok().body(service.findByOrderId(id));
  }

  @GetMapping
  public ResponseEntity<Page<OrderDto>> findAll(Pageable pageable) {
    return ResponseEntity.ok().body(service.findAll(pageable));
  }

  @PostMapping
  public ResponseEntity<OrderDto> insert(@RequestBody Order order) {
    Order orderReturned = service.insertOrder(order);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(orderReturned.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}