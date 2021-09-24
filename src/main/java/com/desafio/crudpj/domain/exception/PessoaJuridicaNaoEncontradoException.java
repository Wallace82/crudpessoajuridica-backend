package com.desafio.crudpj.domain.exception;

public class PessoaJuridicaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PessoaJuridicaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PessoaJuridicaNaoEncontradoException(Long postId) {
		this(String.format("Não existe um cadastro de Pessoa Juridica com código %d", postId));
	}
	
}
