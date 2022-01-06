package com.matheustirabassi.pizzainbox;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Executa o springboot.
 */
@SpringBootApplication
public class PizzainboxApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(PizzainboxApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }

}