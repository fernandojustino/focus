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
import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.TipoPessoa;
import com.focus.repository.TipoPessoaRepository;

@RestController
@RequestMapping("/tipopessoa/")
public class TipoPessoaController {

	@Autowired
	TipoPessoaRepository tipoPessoaRepository; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<TipoPessoa>> listar() {
		List<TipoPessoa> tiposPessoa = tipoPessoaRepository.findAll();
		return new ResponseEntity<List<TipoPessoa>>(new ArrayList<TipoPessoa>(tiposPessoa), HttpStatus.OK);
	}
	
	@PostMapping(path = "/cadastrar/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TipoPessoa> cadastrar(@RequestBody TipoPessoa tipoPessoa)  {
		return new ResponseEntity<TipoPessoa>(tipoPessoaRepository.save(tipoPessoa), HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<?> pesquisar(@PathVariable Integer codigo) {
		return ResponseEntity.ok(tipoPessoaRepository.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletar(@PathVariable Integer codigo) {
		tipoPessoaRepository.delete(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping("/default/") 
	String padrao() {
		TipoPessoa tipoPessoa = new TipoPessoa();		
		tipoPessoa.setNome("Normal");		
		tipoPessoa = tipoPessoaRepository.save(tipoPessoa);
		TipoPessoa tipoPessoa1 = new TipoPessoa();		
		tipoPessoa1.setNome("Normal");		
		tipoPessoa1 = tipoPessoaRepository.save(tipoPessoa1);
		
		
		return "Grava Pessoa !! codigo: " + tipoPessoa1.getId(); 
	}
}



