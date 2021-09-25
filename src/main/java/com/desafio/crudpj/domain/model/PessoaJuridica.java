package com.desafio.crudpj.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name ="PESSOAJURIDICA")
public class PessoaJuridica {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PEJ_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@CNPJ
	@NotEmpty
	@Column(name="PEJ_CNPJ",nullable = false,length = 15)
	private String cnpj;
	
	@NotEmpty(message = "Nome é obrigatório")
	@Column(name="PEJ_NOME",nullable = false,length = 50)
	private String nome;
	
	@NotNull
	@Column(name="PEJ_RAZAOSOCIAL",nullable = false,length = 40)
	private String razaoSocial;
	
	@NotNull
	@Column(name="PEJ_CONTATO",nullable = false,length = 40)
	private String contato;
	
	@NotNull
	@Column(name="PEJ_EMAIL",nullable = false,length = 40)
	private String email;
	
	
	@NotNull
	@Column(name="PEJ_TIPOEMPRESA",nullable = false)
	private TipoEmpresaEnum tipoEmpresa;
	
	@Embedded
	private Endereco endereco;
	

}
