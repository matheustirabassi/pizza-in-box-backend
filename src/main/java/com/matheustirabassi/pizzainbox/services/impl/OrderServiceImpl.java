package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.OrderRepository;
import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  protected GenericRepository<Order> getDao() {
    return orderRepository;
  }

}