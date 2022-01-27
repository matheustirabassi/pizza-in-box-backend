package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.dao.GenericRepository;
import com.matheustirabassi.pizzainbox.dao.OrderItemRepository;
import com.matheustirabassi.pizzainbox.dao.OrderRepository;
import com.matheustirabassi.pizzainbox.dao.PaymentRepository;
import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.domain.OrderItem;
import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import com.matheustirabassi.pizzainbox.domain.enums.PaymentStatus;
import com.matheustirabassi.pizzainbox.dto.OrderDto;
import com.matheustirabassi.pizzainbox.services.OrderService;
import com.matheustirabassi.pizzainbox.services.PaymentBankSlipService;
import com.matheustirabassi.pizzainbox.services.ProductService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private PaymentRepository paymentRepository;

  @Autowired
  private OrderItemRepository ordemItemRepository;

  @Autowired
  private PaymentBankSlipService paymentBankSlipService;

  @Autowired
  private ProductService productService;

  @Override
  protected GenericRepository<Order> getDao() {
    return orderRepository;
  }

  @Transactional
  @Override
  public Order insertOrder(Order order) {
    order.setId(null);
    order.setInstant(new Date());
    order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
    order.getPayment().setOrder(order);

    if (order.getPayment() instanceof PaymentBankSlip paymentBankSlip) {
      paymentBankSlipService.fillPayment(paymentBankSlip, order.getInstant());
    }

    orderRepository.save(order);
    paymentRepository.save(order.getPayment());

    for (OrderItem orderItem : order.getItems()) {
      orderItem.setDiscount(0.0);
      orderItem.setPrice(productService.findById(orderItem.getProduct().getId()).getPrice());
      orderItem.setOrder(order);
    }
    ordemItemRepository.saveAll(order.getItems());
    return order;
  }

  @Transactional(readOnly = true)
  @Override
  public OrderDto findByOrderId(Long id) {
    OrderDto orderDto = new OrderDto(findById(id));
    return orderDto;
  }

  @Transactional(readOnly = true)
  @Override
  public Page<OrderDto> findAll(Pageable pageable) {
    Page<OrderDto> orderDtoPage = findWithPagination(pageable).map(OrderDto::new);
    return orderDtoPage;
  }
}