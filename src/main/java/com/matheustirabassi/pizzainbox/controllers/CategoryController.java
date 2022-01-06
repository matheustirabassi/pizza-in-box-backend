package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.services.impl.CategoryServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryController {

  @Autowired
  private CategoryServiceImpl service;

  @GetMapping(value = "{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Category obj = service.find(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<List<Category>> findAll() {
    List<Category> categories = service.findAll();
    return ResponseEntity.ok().body(categories);
  }
}