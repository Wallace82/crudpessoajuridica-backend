package com.desafio.crudpj.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="PESSOAJURIDICA")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PessoaJuridica {
	
	private Long id;
	private String cnpj;
	private String nome;
	private String razaoSocial;
	private String telefone;
	private String email;
	private Endereco endereco;
	private TipoEmpresaEnum tipoEmpresa;
	
	

}
