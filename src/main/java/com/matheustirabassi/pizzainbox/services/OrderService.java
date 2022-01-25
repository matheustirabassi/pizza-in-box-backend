package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService extends GenericService<Order> {

  Order insertOrder(Order order);

  OrderDto findByOrderId(Long id);

  public Page<OrderDto> findAll(Pageable pageable);
}