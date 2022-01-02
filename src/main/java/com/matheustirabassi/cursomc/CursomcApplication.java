package com.matheustirabassi.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Executa o springboot.
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(CursomcApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
  }

}