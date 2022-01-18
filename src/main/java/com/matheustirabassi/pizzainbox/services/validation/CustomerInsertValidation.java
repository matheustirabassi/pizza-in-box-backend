package com.matheustirabassi.pizzainbox.services.validation;

import com.matheustirabassi.pizzainbox.controllers.exceptions.FieldMessage;
import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.domain.enums.DocumentType;
import com.matheustirabassi.pizzainbox.dto.NewCustomerDto;
import com.matheustirabassi.pizzainbox.services.validation.utils.Br;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Validador do {@link CustomerInsert}.
 */
public class CustomerInsertValidation implements
    ConstraintValidator<CustomerInsert, NewCustomerDto> {

  @Autowired
  private CustomerRepository repo;

  @Override
  public void initialize(CustomerInsert ann) {
  }

  @Override
  public boolean isValid(NewCustomerDto dto, ConstraintValidatorContext context) {

    List<FieldMessage> list = new ArrayList<>();

    if (dto.getDocumentType().equals(DocumentType.PF) && !Br.isValidCPF(dto.getDocument())) {
      list.add(new FieldMessage("document", "CPF inválido"));
    }

    if (dto.getDocumentType().equals(DocumentType.PJ) && !Br.isValidCNPJ(dto.getDocument())) {
      list.add(new FieldMessage("document", "CNPJ inválido"));
    }

    if (repo.findByEmailExists(dto.getEmail())) {
      list.add(new FieldMessage("email", "Email já existente"));
    }

    for (FieldMessage e : list) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
          .addConstraintViolation();
    }
    return list.isEmpty();
  }
}