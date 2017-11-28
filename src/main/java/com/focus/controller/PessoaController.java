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

import com.focus.domain.Cidade;
import com.focus.domain.Estado;
import com.focus.domain.Pessoa;
import com.focus.domain.TipoPessoa;
import com.focus.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository; 
	
	@GetMapping(path = "/{codigo}")
	public ResponseEntity<?> pesquisar(@PathVariable Long codigo) {
		return ResponseEntity.ok(pessoaRepository.findOne(codigo));
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletar(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path = "/")	
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return new ResponseEntity<List<Pessoa>>(new ArrayList<Pessoa>(pessoas), HttpStatus.OK);
	}

	@PostMapping(path = "/cadastrar/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Pessoa> cadastrar(@RequestBody Pessoa pessoa)  {
		return new ResponseEntity<Pessoa>(pessoaRepository.save(pessoa), HttpStatus.CREATED);
	}	
	
	@RequestMapping("/default/") 
	String padrao() {
		Pessoa pessoa = new Pessoa();
		Cidade cidade = new Cidade(); 
		Estado estado = new Estado();
		TipoPessoa tipoPessoa = new TipoPessoa();
		
		estado.setNome("Minas Gerais");
		estado.setSigla("MG");

		cidade.setNome("Uberlandia");
		cidade.setEstado(estado);
				
		tipoPessoa.setNome("Normal");
		
		pessoa.setNome("Fernando");
		pessoa.setCnpj("80614396620");
		pessoa.setLogradouro("Rua ");
		pessoa.setTelefone("34998145231");
		
		pessoa.setCidade(cidade);
		pessoa.setTipoPessoa(tipoPessoa);
		
		pessoa = pessoaRepository.save(pessoa); 
		return "Grava Pessoa !! codigo: " + pessoa.getId(); 
	}
}

