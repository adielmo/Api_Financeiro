package com.rabelo.financeiro.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabelo.financeiro.model.Lancamento;
import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.repository.LancamentoRepository;
import com.rabelo.financeiro.repository.PessoaRepository;
import com.rabelo.financeiro.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoaCodigo = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

		if (!pessoaCodigo.isPresent() || pessoaCodigo.get().isInativo()) {

			throw new PessoaInexistenteOuInativaException();

		}
		return lancamentoRepository.save(lancamento);
	}

}
