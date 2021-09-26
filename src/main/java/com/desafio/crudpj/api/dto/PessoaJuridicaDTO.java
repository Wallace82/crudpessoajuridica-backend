package com.desafio.crudpj.api.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.desafio.crudpj.domain.model.Endereco;
import com.desafio.crudpj.domain.model.TipoEmpresaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PessoaJuridicaDTO {
	private Long id;
	private String cnpj;
	private String nome;
	private String razaoSocial;
	private String contato;
	private String email;
	@Enumerated(EnumType.STRING)
	private TipoEmpresaEnum tipoEmpresa;
	private Endereco endereco;
	private Long matrizId;
	
	
}
