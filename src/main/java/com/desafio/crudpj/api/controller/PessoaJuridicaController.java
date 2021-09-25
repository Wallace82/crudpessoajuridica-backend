package com.desafio.crudpj.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.api.dto.input.PessoaJuridicaInput;
import com.desafio.crudpj.core.mapper.MapperPessoaJuridica;
import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.service.PessoaJuridicaService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("cadastro")
public class PessoaJuridicaController {

	@Autowired
	private PessoaJuridicaService pessoaJuridicaService;
	
	@Autowired
	private MapperPessoaJuridica mapperPessoaJuridica;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscar(@PathVariable Long id) {
		try {
			 PessoaJuridica pessoaJuridica =  pessoaJuridicaService.buscar(id);
			 
			 return ResponseEntity.ok(mapperPessoaJuridica.mapperPessoaJuridica(pessoaJuridica));
		} catch (PessoaJuridicaNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Retorna uma lista paginada de Pessoas Jurídicas conforme filtro")
	@GetMapping
	public ResponseEntity<Page<PessoaJuridicaDTO>>  filtrar(
			PessoaJuridicaFilter pessoaJuridicaFilter,
			Pageable pageable
			) {
		
		
		List<PessoaJuridica> posts =  pessoaJuridicaService.buscarPessoasJuridicasParametro(pessoaJuridicaFilter,pageable);
		List<PessoaJuridicaDTO> retorno = mapperPessoaJuridica.mapperPessoaJuridicaList(posts);
		
		int pageSize = pageable.getPageSize();
		long pageOffset = pageable.getOffset();
		long total = pageOffset + retorno.size() + (retorno.size() == pageSize ? pageSize : 0);
		Page<PessoaJuridicaDTO> page = new PageImpl<PessoaJuridicaDTO>(retorno, pageable,total);
		
		return ResponseEntity.ok().body(page) ;
	}
	
	
	
	@PostMapping()
	public ResponseEntity<?>  cadastro(@RequestBody @Valid PessoaJuridicaInput pessoaJuridicaInput) throws IOException {
		PessoaJuridicaDTO pessoaJuridicaDTO = mapperPessoaJuridica.mapperPessoaJuridicaInput(pessoaJuridicaInput);
		pessoaJuridicaDTO.setId(0l);
		PessoaJuridica pessoaJuridica = pessoaJuridicaService.cadastrar(mapperPessoaJuridica.mapperPostDto(pessoaJuridicaDTO));
		return  buscar(pessoaJuridica.getId());
		
	}
	
	@DeleteMapping()
	public ResponseEntity<?>  excluir(Long id) throws IOException {
		
		try {
			PessoaJuridica pessoaJuridica =  pessoaJuridicaService.buscar(id);
			pessoaJuridicaService.delete(pessoaJuridica);
			return ResponseEntity.ok("Excluido com sucesso");
		} catch (PessoaJuridicaNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>("Erro ao excluir Pessoa Jurírica ", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
	
	
}
