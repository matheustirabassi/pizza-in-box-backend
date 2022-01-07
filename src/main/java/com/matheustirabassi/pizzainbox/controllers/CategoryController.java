package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.services.impl.CategoryServiceImpl;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categories")
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

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody Category obj) {
    Category category = service.saveOrUpdate(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(category.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }
}