package com.desafio.crudpj.domain.model;

public enum TipoEmpresaEnum {

	MATRIZ(0,"Matriz"),
	FILIAL(1,"Filial");
	
	private Integer id;
	private String descricao;
	
	
	private TipoEmpresaEnum(Integer id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
