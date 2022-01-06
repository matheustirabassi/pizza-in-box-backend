package com.matheustirabassi.pizzainbox.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_payment_payment_bank_slip")
public class PaymentBankSlip extends Payment {

  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date dueDate;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date paymentDate;

}