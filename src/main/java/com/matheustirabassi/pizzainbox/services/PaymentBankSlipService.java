package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import java.util.Date;

public interface PaymentBankSlipService {

  void fillPayment(PaymentBankSlip paymentBankSlip, Date instantOrder);
}