package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@JsonTypeName("paymentCard")
@Data
@Entity
@Table(name = "tb_payment_payment_card")
public class PaymentCard extends Payment {

  private Integer numberInstallments;

}