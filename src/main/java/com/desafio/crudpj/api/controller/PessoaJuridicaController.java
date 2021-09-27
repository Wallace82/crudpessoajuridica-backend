package com.desafio.crudpj.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.api.dto.input.PessoaJuridicaInput;
import com.desafio.crudpj.core.mapper.MapperPessoaJuridica;
import com.desafio.crudpj.domain.exception.NegocioException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaMatrizException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaMatrizObrigatoriaException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.model.TipoEmpresaEnum;
import com.desafio.crudpj.domain.service.PessoaJuridicaService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("cadastro")
@CrossOrigin(maxAge = 1800, origins = {"http://localhost:4200"})
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;

	@Autowired
	private MapperPessoaJuridica mapperPessoaJuridica;


	@ApiOperation(value ="RETORNA UMA EMPRESA CONFORME ID PASADO COMO PARAMETRO")
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscar(@PathVariable Long id) {
		try {
			PessoaJuridicaDTO pessoaJuridica =  pessoaJuridicaService.buscar(id);
			return ResponseEntity.ok(pessoaJuridica);
		} catch (PessoaJuridicaNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


	@ApiOperation(value = "FILTRAR AS EMPRESAS INFOMANDO NUM FILTRO:\r\n"
			+ "○ O SEU CNPJ OU BRANCO PARA TODOS;\r\n"
			+ "○ O TIPO: MATRIZ OU FILIAL OU TODOS;\r\n"
			+ "○ O SEU NOME OU VAZIO PARA TODOS. CASO INFORMADO, O FILTRO POR NOME\r\n"
			+ "DEVE SER PARTE DO NOME CADASTRADO. EXEMPLO: CADASTRO TESTE; FILTRO\r\n"
			+ "INFORMADO TES ")
	@PostMapping("/filtrar")
	public Page<PessoaJuridicaDTO> getAll(
			PessoaJuridicaFilter pessoaJuridicaFilter,
			@RequestParam(
					value = "page",
					required = false,
					defaultValue = "0") int page,
			@RequestParam(
					value = "size",
					required = false,
					defaultValue = "5") int size)  {
		return pessoaJuridicaService.filtrar(pessoaJuridicaFilter,page,size);
	}
	
	
	@ApiOperation(value = "BUSCAR AS EMPRESAS MATRIZES:")
	@GetMapping("/matrizes")
	public List<PessoaJuridicaDTO> getAllMatrizes(){
		return pessoaJuridicaService.buscarMatriz();
	}



	@ApiOperation(value ="CADASTRAR UMA NOVA EMPRESA")
	@PostMapping()
	public ResponseEntity<?>  cadastrar(
			@RequestBody @Valid PessoaJuridicaInput pessoaJuridicaInput) 
					throws IOException {
		PessoaJuridicaDTO pessoaJuridicaDTO = 
				mapperPessoaJuridica.mapperPessoaJuridicaInput(pessoaJuridicaInput);

		PessoaJuridica pessoaJuridica  = setarMatriz(pessoaJuridicaInput,pessoaJuridicaDTO);
		
		try {
			pessoaJuridica.setId(0l);
			pessoaJuridica = pessoaJuridicaService.cadastrar(pessoaJuridica);
			
			return  buscar(pessoaJuridica.getId());
		
		} catch (NegocioException msg) {
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.CONFLICT);
		
		} 

	}




	@ApiOperation(value ="EDITAR INFORMAÇOES DE UMA EMPRESA")
	@PutMapping()
	public ResponseEntity<?>  editar(
			@RequestBody @Valid PessoaJuridicaInput pessoaJuridicaInput) throws IOException {

		PessoaJuridicaDTO pessoaJuridicaDTO = mapperPessoaJuridica.mapperPessoaJuridicaInput(pessoaJuridicaInput);
		
		try {
			PessoaJuridica pessoaJuridica  = setarMatriz(pessoaJuridicaInput,pessoaJuridicaDTO);
			pessoaJuridica = mapperPessoaJuridica.mapperPostDto(pessoaJuridicaDTO);
			pessoaJuridica = pessoaJuridicaService.cadastrar(pessoaJuridica);
			return  buscar(pessoaJuridica.getId());
		}catch (PessoaJuridicaNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@ApiOperation(value ="EXCLUIR A EMPRESA")
	@DeleteMapping("/{id}")
	public ResponseEntity<?>  excluir(@PathVariable Long id) throws IOException {

		try {
			PessoaJuridica pessoaJuridica =  pessoaJuridicaService.buscarEntity(id);
			pessoaJuridicaService.delete(pessoaJuridica);
			
			return ResponseEntity.ok("Excluido com sucesso");
			
		} catch (PessoaJuridicaMatrizException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.BAD_REQUEST);
		}


	}

	

	
	private PessoaJuridica setarMatriz(
			PessoaJuridicaInput pessoaJuridicaInput, 
			PessoaJuridicaDTO pessoaJuridicaDTO) throws PessoaJuridicaMatrizObrigatoriaException{
		PessoaJuridica pessoaJuridica =  new PessoaJuridica();
		pessoaJuridica = mapperPessoaJuridica.mapperPostDto(pessoaJuridicaDTO);
		verificaObrigatoriedadeMatriz(pessoaJuridicaDTO,pessoaJuridicaInput,pessoaJuridica);
		return pessoaJuridica;
	}
	
	private void verificaObrigatoriedadeMatriz(
			PessoaJuridicaDTO pessoaJuridicaDTO,
			PessoaJuridicaInput pessoaJuridicaInput,
			PessoaJuridica pessoaJuridica
			)  {
		
		if(pessoaJuridicaDTO.getTipoEmpresa().equals(TipoEmpresaEnum.FILIAL)) {
				
			if (pessoaJuridicaDTO.getMatrizId()==null || pessoaJuridicaDTO.getMatrizId().equals(0l) ) {
				throw new PessoaJuridicaMatrizObrigatoriaException();
			}
			else {
				PessoaJuridica matriz=  pessoaJuridicaService.buscarEntity(pessoaJuridicaInput.getMatrizId());
				pessoaJuridica.setMatriz(matriz);
			}
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PessoaJuridicaMatrizObrigatoriaException.class)
	public Map<String, String> handlePessoaJuridicaMatrizObrigatoriaException(PessoaJuridicaMatrizObrigatoriaException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("MENSAGEM", ex.getMessage());
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PessoaJuridicaMatrizException.class)
	public Map<String, String> handlePessoaJuridicaMatrizException(PessoaJuridicaMatrizException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("MENSAGEM", ex.getMessage());
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PessoaJuridicaNaoEncontradoException.class)
	public Map<String, String> handlePessoaJuridicaNaoEncontradoException(PessoaJuridicaNaoEncontradoException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("MENSAGEM", ex.getMessage());
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public  Map<String, String> handleJsonErrors(HttpMessageNotReadableException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("MENSAGEM", ex.getMessage());
		return errors;
	}
	

}
