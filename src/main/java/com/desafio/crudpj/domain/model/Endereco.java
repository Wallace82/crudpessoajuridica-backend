package com.desafio.crudpj.domain.model;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Endereco {
	
	
	   @NotBlank(message = "Campo CEP é Obrigatório")
	   @Column(name="PEJ_CEP",nullable = false,length = 9)
	 
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
	  
	   @PrePersist @PreUpdate
	   private void prePersistPreUpdate() {
			if(this.cep!=null){
				this.cep = this.cep.trim().replaceAll("\\.|-|/", "");			
			}
		}
	
}
