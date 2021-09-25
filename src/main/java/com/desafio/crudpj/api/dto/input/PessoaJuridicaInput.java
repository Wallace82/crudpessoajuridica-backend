package com.desafio.crudpj.api.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import com.desafio.crudpj.domain.model.TipoEmpresaEnum;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PessoaJuridicaInput {


	@NotBlank(message = "CNPJ é obrigatório")
	@CNPJ(message = "CNPJ Inválido")
	private String cnpj;


	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Razao social é obrigatório")
	private String razaoSocial;

	@NotBlank(message = "Contato é obrigatório")
	private String contato;

	@NotBlank(message = "E-mail é obrigatório")
	@Email
	private String email;

	@NotNull(message = "Tipo de empresa é obrigatório")
	private TipoEmpresaEnum tipoEmpresa;

	@NotBlank(message = "CEP é Obrigatório")
	private String  enderecoCep;


	@NotBlank(message = "Complemento é Obrigatório")
	private String  enderecoComplemento;


	@NotBlank(message = "Logradouro é Obrigatório")
	private String  enderecoLogradouro;


	@NotBlank(message = "Bairro é Obrigatório")
	private String  enderecoBairro;


	@NotBlank(message = "Cidade é Obrigatório")
	private String  enderecoLocalidade;

	@NotBlank(message = "Estado é Obrigatório")
	private String  enderecoUf;
	
	private Long  matrixId;
	
	

}
