package com.matheustirabassi.pizzainbox.services.validation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida a inserção de novos clientes.
 */
@Constraint(validatedBy = CustomerInsertValidation.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface CustomerInsert {

  String message() default "Erro de validação";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}