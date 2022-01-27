package com.matheustirabassi.pizzainbox.services.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

/**
 * Valida se é CPF ou CNPJ.
 */
@Documented
@ConstraintComposition(OR)
@ReportAsSingleViolation
@CPF
@CNPJ
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface DocumentBr {

  String message() default "O documento não é um CPF ou CNPJ";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}