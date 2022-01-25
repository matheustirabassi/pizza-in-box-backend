package com.matheustirabassi.pizzainbox.services.impl;

import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import com.matheustirabassi.pizzainbox.services.PaymentBankSlipService;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class PaymentBankSlipServiceImpl implements PaymentBankSlipService {

  @Override
  public void fillPayment(PaymentBankSlip paymentBankSlip, Date instantOrder) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(instantOrder);
    calendar.add(Calendar.DAY_OF_MONTH, 7);
    paymentBankSlip.setDueDate(calendar.getTime());
  }
}