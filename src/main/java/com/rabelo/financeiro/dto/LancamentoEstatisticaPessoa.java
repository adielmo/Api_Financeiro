package com.rabelo.financeiro.dto;

import java.math.BigDecimal;

import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.model.TipoLancamento;

public class LancamentoEstatisticaPessoa {

	private TipoLancamento tipo;
	private Pessoa pessoa;
	private BigDecimal total;

	public LancamentoEstatisticaPessoa() {
		// TODO Auto-generated constructor stub
	}

	public LancamentoEstatisticaPessoa(TipoLancamento tipo, Pessoa pessoa, BigDecimal total) {

		this.tipo = tipo;
		this.pessoa = pessoa;
		this.total = total;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
