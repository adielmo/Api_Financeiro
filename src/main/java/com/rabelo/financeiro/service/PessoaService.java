package com.rabelo.financeiro.service;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {

		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		/*
		 * System.out.println("Cliente Atualizar: "+ pessoa); System.out.println("Bd: "+
		 * pessoaSalva);
		 */

		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

		return pessoaRepository.save(pessoaSalva);
	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);

		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
	}

	public Pessoa atualizarParcialmente(Long codigo, Map<String, Object> pessoas) {
		Pessoa pessoaAtual = buscarPessoaPeloCodigo(codigo);

		merge(pessoas, pessoaAtual);
		System.out.println(pessoaAtual);
		return atualizar(codigo, pessoaAtual);
	}

	private void merge(Map<String, Object> pessoas, Pessoa pessoaDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Pessoa pessoaOrigem = objectMapper.convertValue(pessoas, Pessoa.class);

		pessoas.forEach((nomePropiedade, valorPropiedade) -> {
			Field field = ReflectionUtils.findField(Pessoa.class, nomePropiedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, pessoaOrigem);

			ReflectionUtils.setField(field, pessoaDestino, novoValor);

			// System.out.println(nomePropiedade + " = " + valorPropiedade + " ="+
			// novoValor);
			// System.out.println(pessoaDestino);
		});
	}

	private Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(codigo);// .orElse(null);

		if (!pessoaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva.get();
	}

	private Pessoa salvar(Pessoa pessoa, Pessoa pessoaSalva) {
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");

		return pessoaRepository.save(pessoaSalva);

	}

}
