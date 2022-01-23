package com.matheustirabassi.pizzainbox.dto;

import com.matheustirabassi.pizzainbox.domain.Address;
import com.matheustirabassi.pizzainbox.domain.Order;
import com.matheustirabassi.pizzainbox.domain.OrderItem;
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

  private static final long serialVersionUID = 1L;

  private Long id;
  private Date instant;
  private Address deliveryAddress;
  private Set<OrderItem> items = new HashSet<>();

  public OrderDto(Order order) {
    this.id = order.getId();
    this.instant = order.getInstant();
    this.deliveryAddress = order.getDeliveryAddress();
    this.items = order.getItems();
  }

  public static List<OrderDto> convert(List<Order> orders) {
    return orders.stream().map(OrderDto::new).collect(Collectors.toList());

  }
}