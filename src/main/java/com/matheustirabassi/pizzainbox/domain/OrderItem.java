package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheustirabassi.pizzainbox.domain.pk.OrderItemPK;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Table(name = "order_item")
@Entity
public class OrderItem {

  @JsonIgnore
  @EmbeddedId
  private OrderItemPK id = new OrderItemPK();

  @Getter
  @Setter
  private Double discount;

  @Getter
  @Setter
  private Integer amount;

  @Getter
  @Setter
  private Double price;

  @JsonIgnore
  public Order getOrder() {
    return id.getOrder();
  }

  public void setOrder(Order order) {
    id.setOrder(order);
  }

  public Product getProduct() {
    return id.getProduct();
  }

  public void setProduct(Product product) {
    id.setProduct(product);
  }

  public Double getSubTotal() {
    return (price - discount) * amount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    OrderItem other = (OrderItem) obj;
    if (id == null) {
      if (other.id != null) {
        return false;
      }
    } else if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }
}