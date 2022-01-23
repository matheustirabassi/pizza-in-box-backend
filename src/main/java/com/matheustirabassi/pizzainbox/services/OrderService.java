package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.Order;

public interface OrderService extends GenericService<Order> {

  Order insertOrder(Order order);
}