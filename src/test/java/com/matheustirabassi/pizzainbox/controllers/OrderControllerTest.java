package com.matheustirabassi.pizzainbox.controllers;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

  @Mock
  OrderService orderService;

  @InjectMocks
  OrderController orderController;
  private Order order;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    order = new Order();
    order.setId(1L);
  }

  @Test
  void insertTest_AllValid_Sucess() {
    when(orderService.insertOrder(any(Order.class))).thenReturn(order);
    orderController.insert(order);
  }
}