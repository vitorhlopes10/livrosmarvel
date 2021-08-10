package com.api.livrosmarvel.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.livrosmarvel.entities.Usuario;
import com.api.livrosmarvel.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@PostMapping("/Cadastrar")
	public ResponseEntity<Usuario> Cadastrar(@RequestBody Usuario usuario) {
		try {
			boolean isValid = usuarioService.Validar(usuario);

			if (!isValid)
				return ResponseEntity.badRequest().build();

			Usuario obj = usuarioService.Cadastrar(usuario);
			return ResponseEntity.created(null).body(obj);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public ResponseEntity<List<Usuario>> BuscarTodos() {
		try {
			List<Usuario> list = usuarioService.Listar();
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping(value = "/BuscarPorId/{id}")
	public ResponseEntity<Usuario> BuscarPorId(@PathVariable Long id) {
		try {
			Usuario obj = usuarioService.BuscarPorId(id);
			return ResponseEntity.ok().body(obj);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
