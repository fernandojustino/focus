package com.focus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.security.Usuario;
import com.focus.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired 
	private UsuarioRepository usuarioRepository;

	@GetMapping(path = "/{codigo}")
	public ResponseEntity<?> pesquisar(@PathVariable Integer codigo) {
		return ResponseEntity.ok(usuarioRepository.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletar(@PathVariable Integer codigo) {
		usuarioRepository.delete(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listar() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(new ArrayList<Usuario>(usuarios), HttpStatus.OK);
	  }

	@PostMapping(path = "/login/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> verificarLogin(@RequestBody Usuario usuario)  {
		HttpStatus status = HttpStatus.OK;
		Usuario usr = usuarioRepository.findByLogin(usuario.getLogin(), usuario.getPassword()) ;
		if (usr == null ) {
			status = HttpStatus.FORBIDDEN;
		} 
		return new ResponseEntity<> (usr, status);
	}

	@PostMapping(path = "/cadastrar/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario)  {
		return new ResponseEntity<Usuario>(usuarioRepository.save(usuario), HttpStatus.CREATED);
	}
	
	
	@RequestMapping("/default/")
	@ResponseBody
	String home() {
		Usuario usuario = new Usuario();
		
		usuario.setNome("fernando");
		usuario.setLogin("fernando");
		usuario.setPassword("fernando");
		
		usuario = usuarioRepository.save(usuario);
		
		Usuario usuario1 = new Usuario();
		
		usuario1.setNome("luiz");
		usuario1.setLogin("luiz");
		usuario1.setPassword("luiz");
		
		usuario1 = usuarioRepository.save(usuario1);

		return "Grava Usuario !! codigo: " + usuario.getId();
	}
	
	
}
