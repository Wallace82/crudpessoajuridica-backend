package com.desafio.crudpj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.crudpj.core.mapper.MapperPessoaJuridica;
import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.service.PessoaJuridicaService;


@RestController
@RequestMapping("cadastro")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	@Autowired
	private MapperPessoaJuridica mapperPessoaJuridica;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscar(@PathVariable Long id) {
		try {
			 PessoaJuridica pessoaJuridica =  pessoaJuridicaService.buscar(id);
			 
			 return ResponseEntity.ok(mapperPessoaJuridica.mapperPessoaJuridica(pessoaJuridica));
		} catch (PessoaJuridicaNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	
}
