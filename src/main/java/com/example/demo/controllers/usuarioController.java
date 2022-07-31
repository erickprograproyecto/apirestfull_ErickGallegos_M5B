package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.entity.Usuario;
import com.example.demo.models.services.usuarioService;

@RestController
@RequestMapping("/api/usuario")
public class usuarioController {
	
	@Autowired
	private usuarioService usuarioservice;
	
	//crear un nuevo usuario
	@PostMapping
	public ResponseEntity<?> create (@RequestBody Usuario usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioservice.save(usuario));
	}
	
	//leer un usario
	@GetMapping("{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Usuario> oUsuario = usuarioservice.findById(id);
		
		if(!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUsuario);
	}
	
	//editar un usuario
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Usuario usuarioDetails, @PathVariable Long id){
		Optional<Usuario> usuario = usuarioservice.findById(id);
		
		if(!usuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		usuario.get().setNombre(usuarioDetails.getNombre());
		usuario.get().setClave(usuarioDetails.getClave());
		usuario.get().setEmail(usuarioDetails.getEmail());
		usuario.get().setEstado(usuarioDetails.getEstado());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioservice.save(usuario.get()));
		
		
	}
	
	//borrar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		if(!usuarioservice.findById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		usuarioservice.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	//leer todos los usuarios
	@GetMapping
	public List<Usuario> readAll(){
		List<Usuario> usuarios = StreamSupport
				.stream(usuarioservice.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return usuarios;
		
	}

}
