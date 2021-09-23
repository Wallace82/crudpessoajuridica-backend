package com.desafio.crudpj.domain;

public enum TipoEmpresaEnum {

	MATRIZ(1,"Matriz"),
	FILIAL(4,"Filial");
	
	private long id;
	private String descricao;
	
	private TipoEmpresaEnum(long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public long getId() {
		return id;
	}
	
}
