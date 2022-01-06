package com.matheustirabassi.pizzainbox.controllers;

import com.matheustirabassi.pizzainbox.domain.Product;
import com.matheustirabassi.pizzainbox.dto.ProductDto;
import com.matheustirabassi.pizzainbox.services.impl.ProductServiceImpl;
import java.net.URI;
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
@RequestMapping(value = "/produtos")
public class ProductController {

  @Autowired
  private ProductServiceImpl service;

  @GetMapping(value = "{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    Product obj = service.findById(id);
    return ResponseEntity.ok().body(obj);
  }

  @GetMapping
  public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {
    Page<ProductDto> list = service.findAllWithPagination(pageable);
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody ProductDto productDto) {
    Product obj = service.fromDto(productDto);
    obj = service.saveOrUpdate(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody ProductDto objDTO, @PathVariable Long id) {
    Product obj = service.fromDto(objDTO);
    obj.setId(id);
    obj = service.saveOrUpdate(obj);
    return ResponseEntity.noContent().build();

  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id) {
    service.deleteById(id);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    return ResponseEntity.created(uri).build();
  }
}