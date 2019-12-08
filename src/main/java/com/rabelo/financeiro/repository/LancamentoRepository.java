package com.rabelo.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.financeiro.model.Lancamento;
import com.rabelo.financeiro.repository.lancamento.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{
	
	boolean findByDescricaoContaining(String nome);

}
