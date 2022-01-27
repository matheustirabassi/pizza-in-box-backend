package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matheustirabassi.pizzainbox.domain.enums.OrderStatus;
import java.io.Serial;
import java.io.Serializable;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Data
@Entity
@Table(name = "`order`")
public class Order extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
  private Date instant;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
  private Payment payment;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "delivery_address_id")
  private Address deliveryAddress;

  @OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)
  private Set<OrderItem> items = new HashSet<>();

  private Integer orderStatus = OrderStatus.WAITING_PAYMENT.getCode();

  public OrderStatus getOrderStatus() {
    return OrderStatus.valueOf(orderStatus);
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    if (orderStatus != null) {
      this.orderStatus = orderStatus.getCode();
    }
  }

  /**
   * Calcula o total do pedido.
   *
   * @return o valor total do pedido.
   */
  public Double getTotal() {
    double sum = 0.0;
    for (OrderItem x : items) {
      sum += x.getSubTotal();
    }
    return sum;
  }

}