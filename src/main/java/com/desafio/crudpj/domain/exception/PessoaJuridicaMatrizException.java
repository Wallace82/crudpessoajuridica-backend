package com.desafio.crudpj.domain.exception;

public class PessoaJuridicaMatrizException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaJuridicaMatrizException(String mensagem) {
		super(mensagem);
	}
	
	public PessoaJuridicaMatrizException() {
		this(String.format("Empresa matriz não pode ser excluida por ter filiais vinculadas"));
	}
	
}
