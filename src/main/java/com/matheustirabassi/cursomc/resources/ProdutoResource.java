package com.matheustirabassi.cursomc.resources;

import java.net.URI;
import java.util.List;

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

import com.matheustirabassi.cursomc.domain.Produto;
import com.matheustirabassi.cursomc.dto.ProdutoDto;
import com.matheustirabassi.cursomc.services.impl.ProdutoServiceImpl;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoServiceImpl service;

	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Produto obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@GetMapping
	public ResponseEntity<Page<ProdutoDto>> findAll(Pageable pageable){
		Page<ProdutoDto> list = service.findAllWithPagination(pageable);
		return ResponseEntity.ok(list);
	}
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody ProdutoDto produtoDto) {
		Produto obj = service.fromDto(produtoDto);
		obj = service.saveOrUpdate(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ProdutoDto objDTO, @PathVariable Integer id) {
		Produto obj = service.fromDto(objDTO);
		obj.setId(id);
		obj = service.saveOrUpdate(obj);
		return ResponseEntity.noContent().build();

	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> deleteProdutoById(@PathVariable Integer id){
		service.deleteById(id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(uri).build();
	}
}
