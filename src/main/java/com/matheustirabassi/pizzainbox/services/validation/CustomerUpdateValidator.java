package com.matheustirabassi.pizzainbox.services.validation;

import com.matheustirabassi.pizzainbox.controllers.exceptions.FieldMessage;
import com.matheustirabassi.pizzainbox.dao.CustomerRepository;
import com.matheustirabassi.pizzainbox.dto.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class CustomerUpdateValidator implements ConstraintValidator<CustomerUpdate, CustomerDto> {

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private CustomerRepository repo;

  @Override
  public void initialize(CustomerUpdate ann) {
  }

  @Override
  public boolean isValid(CustomerDto dto, ConstraintValidatorContext context) {

    @SuppressWarnings("unchecked") Map<String, String> map = (Map<String, String>) request.getAttribute(
        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    Integer uriId = Integer.parseInt(map.get("id"));

    List<FieldMessage> list = new ArrayList<>();

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