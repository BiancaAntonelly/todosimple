package com.biancaantonelly.todosimple.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biancaantonelly.todosimple.models.Usuario;
import com.biancaantonelly.todosimple.models.Usuario.CreateUsuario;
import com.biancaantonelly.todosimple.models.Usuario.UpdateUsuario;
import com.biancaantonelly.todosimple.services.UsuarioService;

import jakarta.validation.Valid;

//api restful
@RestController
@RequestMapping("/user")
@Validated
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> finfById(@PathVariable Long id){
		Usuario obj = this.usuarioService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("")
	@Validated(Usuario.CreateUsuario.class)
	public ResponseEntity<Void> create(@Valid @RequestBody Usuario obj) {
		this.usuarioService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@PutMapping("{id}")
	@Validated(Usuario.UpdateUsuario.class)
	public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Long id) {
		obj.setId(id);
		this.usuarioService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		this.usuarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
