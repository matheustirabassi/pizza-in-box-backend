package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Category;
import com.matheustirabassi.pizzainbox.dto.CategoryDto;
import com.matheustirabassi.pizzainbox.services.impl.CategoryServiceImpl;
import com.matheustirabassi.pizzainbox.utils.ObjectMapperUtils;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<List<CategoryDto>> findAll() {
    List<Category> categories = service.findAll();
    return ResponseEntity.ok().body(
        ObjectMapperUtils.mapAll(categories, CategoryDto.class));
  }

  @GetMapping("/page")
  public ResponseEntity<Page<CategoryDto>> findPage(Pageable pageable) {
    Page<Category> categories = service.findWithPagination(pageable);
    return ResponseEntity.ok().body(categories.map(CategoryDto::new));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDto categoryRequest) {
    Category category = service.saveOrUpdate(service.fromDTO(categoryRequest));
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(category.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping
  public ResponseEntity<Void> update(@Valid @RequestBody CategoryDto categoryRequest) {
    service.updateCategory(categoryRequest);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}