package com.desafio.crudpj.domain.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name ="PESSOAJURIDICA")
public class PessoaJuridica {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PEJ_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@CNPJ
	@NotEmpty
	@Column(name="PEJ_CNPJ",unique=true,nullable = false,length = 15)
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
	@Enumerated(EnumType.STRING)
	private TipoEmpresaEnum tipoEmpresa;
	
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PEJ_CODIGO_MATRIZ",referencedColumnName = "PEJ_CODIGO",nullable = true)
	private PessoaJuridica matriz;


	public PessoaJuridica(Long id, String cnpj, String nome,
			String razaoSocial, String contato, String email, TipoEmpresaEnum tipoEmpresa, Endereco endereco,
			PessoaJuridica matriz) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.contato = contato;
		this.email = email;
		this.tipoEmpresa = tipoEmpresa;
		this.endereco = endereco;
		this.matriz = matriz;
		
	}
//15.340.235/0001-08
	
	@PrePersist @PreUpdate
	private void prePersistPreUpdate() {
		if(this.cnpj!=null){
			this.cnpj = this.cnpj.trim().replaceAll("\\.|-|/", "");			
		}
	}
	
	@PostLoad
	private void postLoad() {
		this.cnpj = (this.cnpj!=null?this.cnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})", "$1.$2.$3/$4-").trim():null); 
	}

}
