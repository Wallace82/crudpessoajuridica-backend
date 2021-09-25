package com.desafio.crudpj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.crudpj.api.consumers.CepService;
import com.desafio.crudpj.api.dto.viacep.Endereco;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/buscarcep")
public class CepController {

	@Autowired
    private CepService cepService;

	@ApiOperation(value = "Buscar um cep em API externa Via CEP")
    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> getCep(@PathVariable String cep) {
        Endereco endereco = cepService.buscaEnderecoPorCep(cep);
        return endereco != null ? ResponseEntity.ok().body(endereco) : ResponseEntity.notFound().build(); 
    }
	
}
