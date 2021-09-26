package com.desafio.crudpj.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio.crudpj.api.dto.PessoaJuridicaDTO;
import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;

public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, Long>{

	@Query("    select new com.desafio.crudpj.api.dto.PessoaJuridicaDTO"
			+ "     (pj.id,pj.cnpj,pj.nome,pj.razaoSocial,"
			+ "      pj.contato,pj.email,pj.tipoEmpresa,"
			+ "      pj.endereco,pj.matriz.id)"
			+ " from PessoaJuridica pj " 
			+ " where "
			+ "      (:#{#filter?.nomeEmpresa} is null or pj.nome like %:#{#filter.nomeEmpresa}% ) "
			+ "    and (:#{#filter?.cnpj} is null or pj.cnpj = :#{#filter.cnpj}  )  "
			+ "    and (:#{#filter?.tipoEmpresa} is null or pj.tipoEmpresa = :#{#filter.tipoEmpresa} )  "
			+ " order by pj.nome asc  "
			)
	Page<PessoaJuridicaDTO> filtrar( 
			PessoaJuridicaFilter filter, 
			Pageable pageable);

	@Query("select new com.desafio.crudpj.api.dto.PessoaJuridicaDTO"
			+ "(pj.id,pj.cnpj,pj.nome,pj.razaoSocial,pj.contato,pj.email,pj.tipoEmpresa,"
			+ "pj.endereco,pj.matriz.id"
			+ ") from PessoaJuridica pj " 
			+  " where pj.id = :id "
			)
	Optional<PessoaJuridicaDTO> buscarPorId(Long id);

	Optional<PessoaJuridica> findByCnpj(String cnpj);

	
	@Query("	select new com.desafio.crudpj.api.dto.PessoaJuridicaDTO"
			+ "	   (pj.id,pj.cnpj,pj.nome,pj.razaoSocial,"
			+ "	    pj.contato,pj.email,pj.tipoEmpresa,"
			+ "	    pj.endereco,pj.matriz.id)"
			+ "  from PessoaJuridica pj " 
			+  " where "
			+ "   ( pj.tipoEmpresa = 'MATRIZ' )  "
			+ " order by pj.nome asc  "
			)
	List<PessoaJuridicaDTO> buscarMatriz();

	List<PessoaJuridica> findByMatrizId(Long id);






}
