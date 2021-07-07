package com.rabelo.financeiro.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rabelo.financeiro.dto.LancamentoEstatisticaCategoria;
import com.rabelo.financeiro.dto.LancamentoEstatisticaDia;
import com.rabelo.financeiro.dto.LancamentoEstatisticaPessoa;
import com.rabelo.financeiro.model.Lancamento;
import com.rabelo.financeiro.repository.filter.LancamentoFilter;
import com.rabelo.financeiro.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {
	
	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);

	public List<LancamentoEstatisticaDia> porCategoriaDia(LocalDate porDia);
	
	public List<LancamentoEstatisticaCategoria> porCategoria(LocalDate porMes);
	
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
	
	

}
