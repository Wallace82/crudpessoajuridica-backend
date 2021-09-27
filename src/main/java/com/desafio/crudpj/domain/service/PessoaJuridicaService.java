package com.desafio.crudpj.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.domain.exception.NegocioException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaMatrizException;
import com.desafio.crudpj.domain.exception.PessoaJuridicaNaoEncontradoException;
import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;
import com.desafio.crudpj.domain.model.TipoEmpresaEnum;
import com.desafio.crudpj.domain.repository.PessoaJuridicaRepository;

/**
 * @author Wallace
 *
 */
/**
 * @author Wallace
 *
 */
@Service
public class PessoaJuridicaService {

	@Autowired
	private PessoaJuridicaRepository pessoaJuridicaRepository;

	/**
	 * BUSCA UMA EMPRESA PELO ID PASSADO SE NãO EXISTIR LANÇA EXCEÇAO (PessoaJuridicaNaoEncontradoException)
	 * @param id
	 * @return Empresa DTO com todos os dados
	 */
	public PessoaJuridicaDTO buscar(Long id) {
		return buscarOuFalhar(id);
	}
	
	
	/**
	 * BUSCA UMA EMPRESA PELO ID PASSADO SE NãO EXISTIR LANÇA EXCEÇAO (PessoaJuridicaNaoEncontradoException)
	 * @param id
	 * @return Empresa  com todos os dados
	 */
	public PessoaJuridica buscarEntity(Long id) {
		return pessoaJuridicaRepository.findById(id)
				.orElseThrow(() -> new PessoaJuridicaNaoEncontradoException(id));
	}


	/**
	 * Cadastrar e/ou editar uma empresa no caso é verificada aexistencia do CNPJ no banco de dados
	 * @param pessoaJuridica
	 * @return Empresa correspondente ao cdastro
	 */
	public PessoaJuridica cadastrar(PessoaJuridica pessoaJuridica) {
		Optional<PessoaJuridica> pessoaJuridicaExistente = 
				pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj().replaceAll("\\.|-|/", ""));
		if (pessoaJuridicaExistente.isPresent()
				&& !pessoaJuridicaExistente.get().getId().equals(pessoaJuridica.getId())) {
			throw new NegocioException(
					String.format("Já existe uma empresa cadastrado com o CNPJ %s", pessoaJuridica.getCnpj()));
		}
		return pessoaJuridicaRepository.save(pessoaJuridica);
	}
	
	
	/**
	 * LISTA PAGINADA COMFORME FILTRO APLICADO
	 * @param pessoaJuridicaFilterFilter(Nome, CNPJ, Tipo de Empresa)
	 * @param page
	 * @param size
	 * @return Page com tamanho de cinco itens e dados de paginação
	 */
	public Page<PessoaJuridicaDTO> filtrar(
			PessoaJuridicaFilter pessoaJuridicaFilter,
			int page,
			int size) {
		PageRequest pageRequest = PageRequest.of(
				page,
				size,
				Sort.Direction.ASC,
				"nome");
		pessoaJuridicaFilter.setCnpj(pessoaJuridicaFilter.getCnpj().trim().replaceAll("\\.|-|/", ""));
		
		return pessoaJuridicaRepository.filtrar(
				pessoaJuridicaFilter,
				pageRequest);
	}
	
	/**
	 * Deleta uma empresa do banco de dados caso seja uma Matrix com Filial vinculada 
	 * lança uma exeção  PessoaJuridicaMatrizException
	 * @param pessoaJuridica
	 */
	public void delete(PessoaJuridica pessoaJuridica) {
		if (pessoaJuridica.getTipoEmpresa().equals(TipoEmpresaEnum.MATRIZ)){
			List<PessoaJuridica>  filiais = pessoaJuridicaRepository.findByMatrizId(pessoaJuridica.getId());
			if(!filiais.isEmpty()) {
				throw new PessoaJuridicaMatrizException();
			}
			
		}
		pessoaJuridicaRepository.delete(pessoaJuridica);
	}
	

	/**
	 * @param id
	 * @return 
	 */
	public PessoaJuridicaDTO buscarOuFalhar(Long id) {
		Optional<PessoaJuridicaDTO> retorno = pessoaJuridicaRepository.buscarPorId(id);
		if(!retorno.isPresent()) {
			new PessoaJuridicaNaoEncontradoException(id);
		}
		return retorno.get();

	}
	
	
	public List<PessoaJuridicaDTO> buscarMatriz() {
		return pessoaJuridicaRepository.buscarMatriz();
	}

}
