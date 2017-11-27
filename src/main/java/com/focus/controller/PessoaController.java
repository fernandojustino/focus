package com.focus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.Cidade;
import com.focus.domain.Estado;
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
		System.out.println(pessoas.get(1).getCidade().getNome());
		return new ResponseEntity<List<Pessoa>>(new ArrayList<Pessoa>(pessoas), HttpStatus.OK);
	}
	
	@RequestMapping("/default/") 
	String padrao() {
		Pessoa pessoa = new Pessoa();
		Cidade cidade = new Cidade(); 
		Estado estado = new Estado();
		
		pessoa.setNome("Fernando");
		pessoa.setCnpj("80614396620");
		pessoa.setLogradouro("Rua ");
		pessoa.setTelefone("34998145231");
		
		
		cidade.setNome("teste");
		estado.setNome("estado");
		
		cidade.setEstado(estado);
		
		pessoa.setCidade(cidade);
		
		pessoa = pessoaRepository.save(pessoa); 
		return "Grava Pessoa !! codigo: " + pessoa.getId(); 
	}
}



