package com.desafio.crudpj.domain.model;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Endereco {
	
	
	 @NotBlank(message = "Campo CEP é Obrigatório")
	   @Column(name="PEJ_CEP",nullable = false,length = 8)
	   private String  cep;
		
	   
	   @NotBlank(message = "Campo Complemento é Obrigatório")
		@Column(name="PEJ_ENDERECO_COMPLEMENTO",nullable = false,length = 40)
	   private String  complemento;
	   
		
		 @NotBlank(message = "Campo Logradouro é Obrigatório")
	   @Column(name="PEJ_ENDERECO_LOGRADOURO",nullable = false,length = 40)
	   private String  logradouro;
	   
	   
	   @NotBlank(message = "Campo Bairro é Obrigatório")
	   @Column(name="PEJ_ENDERECO_BAIRRO",nullable = false,length = 40)
	   private String  bairro;
	   
	   
	   @NotBlank(message = "Campo Cidade é Obrigatório")
	   @Column(name="PEJ_ENDERECO_CIDADE",nullable = false,length = 40)
	   private String  localidade;
	   
	   @NotBlank(message = "Campo Estado é Obrigatório")
	   @Column(name="PEJ_ENDERECO_UF",nullable = false,length = 40)
	   private String  uf;
	   
	  
	   
	
}
