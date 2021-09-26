package com.desafio.crudpj.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.domain.exception.NegocioException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaMatrizException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.model.TipoEmpresaEnum;
import com.desafio.crudpj.domain.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	public PessoaJuridicaDTO buscar(Long id) {
		return buscarOuFalhar(id);
	}


	public List<PessoaJuridica> buscarPessoasJuridicas() {
		return pessoaJuridicaRepository.findAll();
	}

	public PessoaJuridica cadastrar(PessoaJuridica pessoaJuridica) {
		
		Optional<PessoaJuridica> pessoaJuridicaExistente = pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj());
		if (pessoaJuridicaExistente.isPresent()
				&& !pessoaJuridicaExistente.get().equals(pessoaJuridica)) {
			throw new NegocioException(
					String.format("Já existe uma empresa cadastrado com o CNPJ %s", pessoaJuridica.getCnpj()));
		}
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}

	public PessoaJuridicaDTO buscarOuFalhar(Long id) {

		Optional<PessoaJuridicaDTO> retorno = pessoaJuridicaRepository.buscarPorId(id);
		if(!retorno.isPresent()) {
			new PessoaJuridicaNaoEncontradoException(id);
		}
		return retorno.get();

	}

	public PessoaJuridica buscarEntity(Long id) {
		return pessoaJuridicaRepository.findById(id)
				.orElseThrow(() -> new PessoaJuridicaNaoEncontradoException(id));
	}


	public void delete(PessoaJuridica pessoaJuridica) {
		if (pessoaJuridica.getTipoEmpresa().equals(TipoEmpresaEnum.MATRIZ)){
			System.err.println("erro matriz");
			throw new PessoaJuridicaMatrizException();
		}
		pessoaJuridicaRepository.delete(pessoaJuridica);
	}


	public Page<PessoaJuridicaDTO> filtrar(
			PessoaJuridicaFilter pessoaJuridicaFilterFilter,
			int page,
			int size) {

		PageRequest pageRequest = PageRequest.of(
				page,
				size,
				Sort.Direction.ASC,
				"nome");

		return pessoaJuridicaRepository.filtrar(
				pessoaJuridicaFilterFilter,
				pageRequest);
	}



}
