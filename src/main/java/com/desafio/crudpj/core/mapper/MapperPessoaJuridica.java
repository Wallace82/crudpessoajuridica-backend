package com.desafio.crudpj.core.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.api.dto.input.PessoaJuridicaInput;
import com.desafio.crudpj.domain.model.PessoaJuridica;

@Component
public class MapperPessoaJuridica {

	@Autowired
	private ModelMapper modelMapper;
	
	
	public PessoaJuridicaDTO mapperPessoaJuridica(PessoaJuridica pessoaJuridica) {
		PessoaJuridicaDTO pessoaJuridicaDTO = modelMapper.map(pessoaJuridica, PessoaJuridicaDTO.class);
		return pessoaJuridicaDTO;
	}
	
	public List<PessoaJuridicaDTO> mapperPessoaJuridicaList(List<PessoaJuridica> pessoasJuridicas) {
		List<PessoaJuridicaDTO> retorno = pessoasJuridicas
				  .stream()
				  .map(pj -> mapperPessoaJuridica(pj))
				  .collect(Collectors.toList());
		return retorno;
	}

	public PessoaJuridica mapperPostDto(PessoaJuridicaDTO pessoaJuridicaDTO) {
		return modelMapper.map(pessoaJuridicaDTO, PessoaJuridica.class);
	}
	
	public PessoaJuridicaDTO mapperPessoaJuridicaInput(PessoaJuridicaInput input) {
		return modelMapper.map(input, PessoaJuridicaDTO.class);
	}

	
	
	
}
