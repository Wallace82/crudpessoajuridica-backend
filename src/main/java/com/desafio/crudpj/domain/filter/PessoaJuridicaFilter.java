package com.desafio.crudpj.domain.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PessoaJuridicaFilter {
	private String cnpj;
	private String nomeEmpresa;
}
