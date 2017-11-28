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

import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.Estado;
import com.focus.repository.EstadoRepository;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {

	@Autowired
	EstadoRepository estadoRepository; 
	
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<?> pesquisar(@PathVariable Integer codigo) {
		return ResponseEntity.ok(estadoRepository.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletar(@PathVariable Integer codigo) {
		estadoRepository.delete(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/")	
	public ResponseEntity<List<Estado>> listar() {
		List<Estado> estados = estadoRepository.findAll();
		return new ResponseEntity<List<Estado>>(new ArrayList<Estado>(estados), HttpStatus.OK);
	}

	@PostMapping(path = "/cadastrar/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado)  {
		return new ResponseEntity<Estado>(estadoRepository.save(estado), HttpStatus.CREATED);
	}	
	
	@RequestMapping("/default/") 
	String padrao() {
		Estado estado = new Estado();
		estado.setNome("Minas Gerais");
		estado.setSigla("MG");
		estado = estadoRepository.save(estado);
		
		Estado estado1 = new Estado();
		estado1.setNome("Minas Gerais");
		estado1.setSigla("MG");
		
		estado = estadoRepository.save(estado1);
				
		return "Grava Estado !! codigo: " + estado1.getId(); 
	}
	
}
