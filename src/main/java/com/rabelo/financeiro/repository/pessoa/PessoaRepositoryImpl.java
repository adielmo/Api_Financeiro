package com.rabelo.financeiro.repository.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.model.Pessoa_;
import com.rabelo.financeiro.repository.filter.PessoaFilter;

public class PessoaRepositoryImpl implements PessoaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Pessoa> filtrar(Pageable pageable, PessoaFilter pessoaFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Pessoa> criteria = builder.createQuery(Pessoa.class);
		
		Root<Pessoa> root = criteria.from(Pessoa.class);
		
		
		Predicate[] predicates = criarSelacao(pessoaFilter, builder, root);
	
		criteria.where(predicates);
		
		
		criteria.orderBy(builder.desc(root.get(Pessoa_.nome)));

		TypedQuery<Pessoa> query = manager.createQuery(criteria);
		adicianarRestricoesDePaginacao(pageable, query);

		return new PageImpl<>(query.getResultList(), pageable, total(pessoaFilter));
	}

	private void adicianarRestricoesDePaginacao(Pageable pageable, TypedQuery<Pessoa> query) {
		int paginaAtual = pageable.getPageNumber();
		int totalDeRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalDeRegistroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalDeRegistroPorPagina);
		
		

	}

	private Long total(PessoaFilter pessoaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Pessoa> root = criteria.from(Pessoa.class);

		Predicate[] predicates = criarSelacao(pessoaFilter, builder, root);

		criteria.where(predicates);
		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private Predicate[] criarSelacao(PessoaFilter pessoaFilter, CriteriaBuilder builder, Root<Pessoa> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(pessoaFilter.getNome())) {
			predicates.add(
		builder.like(builder.lower(root.get(Pessoa_.nome)),
					"%" + pessoaFilter.getNome().toLowerCase() + "%"));

		}

		
		  if (!StringUtils.isEmpty(pessoaFilter.getBairro())) {
			/*
			 * predicates.add(builder.like(builder.lower(root.get("bairro")), "%" +
			 * pessoaFilter.getBairro().toLowerCase() + "%"));
			 */ 
					  
		  }
		 
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
