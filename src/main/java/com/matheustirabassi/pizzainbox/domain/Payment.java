package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matheustirabassi.pizzainbox.domain.enums.PaymentStatus;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Super classe dos tipos de pagamento.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tb_payment")
public abstract class Payment extends BaseEntity {

  private Integer paymentStatus;

  @JsonIgnore
  @JoinColumn(name = "order_id")
  @OneToOne
  @MapsId
  private Order order;

  public PaymentStatus getPaymentStatus() {
    return PaymentStatus.toEnum(paymentStatus);
  }

  public void setPaymentStatus(PaymentStatus paymentStatus) {
    this.paymentStatus = paymentStatus.getCod();
  }

}