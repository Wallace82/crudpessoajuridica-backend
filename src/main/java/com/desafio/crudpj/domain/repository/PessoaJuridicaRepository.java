package com.desafio.crudpj.domain.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.desafio.crudpj.domain.filter.PessoaJuridicaFilter;
import com.desafio.crudpj.domain.model.PessoaJuridica;

public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, Long>{

	 
	 @Query("select  pj from PessoaJuridica pj " 
			 +  SEARCH_WHERE
            + " order by pj.nome asc  " 
            )
	 List<PessoaJuridica> findByFilter(PessoaJuridicaFilter filter, Pageable pageable);
	

 static final String SEARCH_WHERE = 
			 " where 1 = 1 "
			            + " and (:#{#filter?.nome} = '' or pj.nome like %:#{#filter.nome}% ) "
			            + " and (:#{#filter?.cnpj} = '' or pj.cnpj like %:#{#filter.cnpj}% )  ";

}
