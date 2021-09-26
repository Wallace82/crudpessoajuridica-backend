package com.desafio.crudpj.domain.exception;

public class PessoaJuridicaMatrizObrigatoriaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaJuridicaMatrizObrigatoriaException(String mensagem) {
		super(mensagem);
	}
	
	public PessoaJuridicaMatrizObrigatoriaException() {
		this(String.format("Cadastro de matriz obrigatório"));
	}
	
}
