package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.domain.OrderItem;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private Long id;
  private Date instant;
  private AddressDto deliveryAddress;
  private Double totalOrder;
  private Set<OrderItem> items = new HashSet<>();

  public OrderDto(Order order) {
    id = order.getId();
    instant = order.getInstant();
    deliveryAddress = new AddressDto(order.getDeliveryAddress());
    totalOrder = order.getTotal();
    items = order.getItems();
  }

  public static List<OrderDto> convert(List<Order> orders) {
    return orders.stream().map(OrderDto::new).collect(Collectors.toList());

  }
}