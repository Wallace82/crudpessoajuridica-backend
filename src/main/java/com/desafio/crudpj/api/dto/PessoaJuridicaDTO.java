package com.desafio.crudpj.api.dto;

import com.desafio.crudpj.domain.model.Endereco;
import com.desafio.crudpj.domain.model.TipoEmpresaEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaJuridicaDTO {

	private Long id;
	private String cnpj;
	private String nome;
	private String razaoSocial;
	private String contato;
	private String email;
	private TipoEmpresaEnum tipoEmpresa;
	private Endereco endereco;
	
}
