package com.desafio.crudpj.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ENDERECO")
public class Endereco {
	
	   private String  cep;
	   private String  complemento;
	   private String  logradouro;
	   private String  bairro;
	   private String  localidade;
	   private String  uf;
	   private String  ibge;
	   private String  gia;
	   private String  ddd;
	   private String  siafi;
	   private String  numero;
	   
	
}
