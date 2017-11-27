package com.focus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.focus.domain.TipoCliente;
import com.focus.repository.TipoClienteRepository;

@RestController
@RequestMapping("/tipoCliente/")
public class TipoClienteController {

	@Autowired
	TipoClienteRepository tipoClienteRepository; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<TipoCliente>> listar() {
		List<TipoCliente> tiposCliente = tipoClienteRepository.findAll();
		return new ResponseEntity<List<TipoCliente>>(new ArrayList<TipoCliente>(tiposCliente), HttpStatus.OK);
	}
	
	
	@RequestMapping("/default/") 
	String padrao() {
		TipoCliente tipoCliente = new TipoCliente();		
		tipoCliente.setNome("Normal");		
		tipoCliente = tipoClienteRepository.save(tipoCliente);
		TipoCliente tipoCliente1 = new TipoCliente();		
		tipoCliente1.setNome("Especial");		
		tipoCliente1 = tipoClienteRepository.save(tipoCliente1);
		return "Grava Pessoa !! codigo: " + tipoCliente1.getId(); 
	}
}



