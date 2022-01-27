package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.dao.OrderItemRepository;
import com.matheustirabassi.pizzainbox.dao.OrderRepository;
import com.matheustirabassi.pizzainbox.dao.PaymentRepository;
import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.domain.Payment;
import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import com.matheustirabassi.pizzainbox.domain.enums.PaymentStatus;
import com.matheustirabassi.pizzainbox.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private PaymentRepository paymentRepository;
  
  @Mock
  private OrderItemRepository orderItemRepository;
  @Mock
  private PaymentBankSlipService paymentBankSlipService;

  @InjectMocks
  private OrderServiceImpl orderService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void InsertOrderTest_AllValid_Sucess() {
    Order order = new Order();
    Payment payment = new PaymentBankSlip();

    payment.setPaymentStatus(PaymentStatus.PENDING);
    order.setPayment(payment);
    orderService.insertOrder(order);
  }
}