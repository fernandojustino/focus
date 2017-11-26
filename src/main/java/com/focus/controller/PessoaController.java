package com.focus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.Pessoa;
import com.focus.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa/")
public class PessoaController {

	@Autowired
	PessoaRepository pessoaRepository; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		return new ResponseEntity<List<Pessoa>>(new ArrayList<Pessoa>(pessoas), HttpStatus.OK);
	}
	
	@RequestMapping("/default/") 
	String padrao() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Fernando");
		pessoa.setCnpj("80614396620");
		pessoa.setLogradouro("Rua ");
		pessoa.setTelefone("34998145231");
		pessoa = pessoaRepository.save(pessoa); 
		return "Grava Pessoa !! codigo: " + pessoa.getId(); 
	}
}



