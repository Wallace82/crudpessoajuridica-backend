package com.desafio.crudpj.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;
	
	public PessoaJuridica buscar(Long id) {
		return buscarOuFalhar(id);
	}
	
	
	public List<PessoaJuridica> buscarPessoasJuridicas() {
		return pessoaJuridicaRepository.findAll();
	}

	public PessoaJuridica cadastrar(PessoaJuridica pessoaJuridica) {
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}

	public PessoaJuridica buscarOuFalhar(Long id) {
		return pessoaJuridicaRepository.findById(id)
				.orElseThrow(() -> new PessoaJuridicaNaoEncontradoException(id));
	}
	
	

}
