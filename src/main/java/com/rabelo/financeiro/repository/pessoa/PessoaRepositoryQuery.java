package com.rabelo.financeiro.repository.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.repository.filter.PessoaFilter;

public interface PessoaRepositoryQuery {
	
	public Page<Pessoa> filtrar(Pageable pageable, PessoaFilter pessoaFilter);

}
