package com.matheustirabassi.pizzainbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matheustirabassi.pizzainbox.domain.PaymentBankSlip;
import com.matheustirabassi.pizzainbox.domain.PaymentCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

  /**
   * Mapeia a serialização do Jackson para {@link com.matheustirabassi.pizzainbox.domain.Payment}.
   *
   * @return o construtor do Jackson.
   */
  @Bean
  public Jackson2ObjectMapperBuilder objectMapperBuilder() {
    return new Jackson2ObjectMapperBuilder() {
      public void configure(ObjectMapper objectMapper) {
        objectMapper.registerSubtypes(PaymentCard.class);
        objectMapper.registerSubtypes(PaymentBankSlip.class);
        super.configure(objectMapper);
      }
    };
  }
}