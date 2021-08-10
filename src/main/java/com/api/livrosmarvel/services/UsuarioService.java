package com.api.livrosmarvel.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.livrosmarvel.entities.Usuario;
import com.api.livrosmarvel.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> Listar() {
		return usuarioRepository.findAll();
	}

	public Usuario BuscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.get();
	}

	public Usuario Cadastrar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public boolean Validar(Usuario usuario) {
		if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
			return false;
		}
		
		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			return false;
		}

		if (usuario.getDataNascimento() == null) {
			return false;
		}

		if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
			return false;
		}
		
		List<Usuario> usuarios = this.Listar();
		
		for (Usuario obj : usuarios) {
			if(obj.getCpf().equals(obj.getCpf())) {
				return false;
			}
			
			if(obj.getEmail().equals(obj.getEmail())) {
				return false;
			}
		}

		return true;
	}

}
