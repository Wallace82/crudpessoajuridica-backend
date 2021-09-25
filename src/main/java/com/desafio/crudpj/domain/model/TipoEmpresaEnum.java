package com.desafio.crudpj.domain.model;

public enum TipoEmpresaEnum {

	MATRIZ(1,"Matriz"),
	FILIAL(2,"Filial");
	
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
