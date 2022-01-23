package com.matheustirabassi.pizzainbox.services;

import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import com.matheustirabassi.pizzainbox.services.impl.PaymentBankSlipServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

public class PaymentBankSlipServiceTest {


  private PaymentBankSlipService paymentBankSlipService;
  private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    paymentBankSlipService = new PaymentBankSlipServiceImpl();
  }

  @Test
  public void fillPaymentTest_AllValid_Sucess() throws Exception {
    PaymentBankSlip payment = new PaymentBankSlip();
    Date orderDate = sdf.parse("23/01/2022 00:00:00");
    paymentBankSlipService.fillPayment(payment, orderDate);
  }

}