package com.desafio.crudpj.api.dto.input;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.desafio.crudpj.domain.model.TipoEmpresaEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PessoaJuridicaInput {

	private Long id;
	
	@NotBlank(message = "CNPJ é obrigatório")
	@CNPJ(message = "CNPJ Inválido")
	private String cnpj;


	@NotBlank(message = "Nome é obrigatório")
	private String nome;

	@NotBlank(message = "Razao social é obrigatório")
	@Size(max = 40,message = "Razao Social deve ser menor que ou igual à 40")
	private String razaoSocial;

	@NotBlank(message = "Contato é obrigatório")
	@Size(max = 11,message = "Razao Contato ser menor que ou igual à 11")
	private String contato;

	@NotBlank(message = "E-mail é obrigatório")
	@Size(max = 40,message = "E-mail deve ser menor que ou igual à 40")
	@Email
	private String email;

	@NotNull(message = "Tipo de empresa é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoEmpresaEnum tipoEmpresa;

	@NotBlank(message = "CEP é Obrigatório")
	@Size(max = 9,min = 8,message = "Cep de ter tamnho entre 8 e 9")
	private String  enderecoCep;


	@NotBlank(message = "Complemento é Obrigatório")
	@Size(max = 40,message = "Complemento deve ser menor que ou igual à 40")
	private String  enderecoComplemento;


	@NotBlank(message = "Logradouro é Obrigatório")
	@Size(max = 40,message = "Logradouro deve ser menor que ou igual à 40")
	private String  enderecoLogradouro;


	@NotBlank(message = "Bairro é Obrigatório")
	@Size(max = 40,message = "Bairro deve ser menor que ou igual à 40")
	private String  enderecoBairro;


	@NotBlank(message = "Cidade é Obrigatório")
	@Size(max = 40,message = "Cidade deve ser menor que ou igual à 40")
	private String  enderecoLocalidade;

	@NotBlank(message = "Estado é Obrigatório")
	@Size(max = 40,message = "Estado deve ser menor que ou igual à 40")
	private String  enderecoUf;
	
	
	private Long matrizId;
	
	

}
