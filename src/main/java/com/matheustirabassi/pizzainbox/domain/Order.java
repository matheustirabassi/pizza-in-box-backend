package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheustirabassi.pizzainbox.domain.enums.OrderStatus;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe de pedido para os produtos
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tb_order")
public class Order extends BaseEntity {

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Date instante;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
  private Payment payment;

  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "delivery_address_Id")
  private Address deliveryAddress;

  @OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
  private Set<OrderItem> itens = new HashSet<>();

  private Integer orderStatus;

  public OrderStatus getOrderStatus() {
    return OrderStatus.valueOf(orderStatus);
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    if (orderStatus != null) {
      this.orderStatus = orderStatus.getCode();
    }
  }

  public Double getTotal() {
    double sum = 0.0;
    for (OrderItem x : itens) {
      sum += x.getSubTotal();
    }
    return sum;
  }

}