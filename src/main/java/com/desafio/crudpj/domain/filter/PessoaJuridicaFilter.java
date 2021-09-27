package com.desafio.crudpj.domain.filter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostLoad;

import com.desafio.crudpj.domain.model.TipoEmpresaEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PessoaJuridicaFilter {
	private String cnpj;
	private String nomeEmpresa;
	
	@Enumerated(EnumType.STRING)
	private TipoEmpresaEnum tipoEmpresa;
	
}

