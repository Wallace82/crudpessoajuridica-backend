package com.desafio.crudpj.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.crudpj.domain.model.PessoaJuridica;

public interface PessoaJuridicaRepository  extends JpaRepository<PessoaJuridica, Long>{

}
