package com.rabelo.financeiro.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rabelo.financeiro.model.Lancamento;
import com.rabelo.financeiro.model.TipoLancamento;

public class LancamentoEstatisticaDia {

	private TipoLancamento tipo;
	private LocalDate dia;
	private BigDecimal total;

	public LancamentoEstatisticaDia() {
		// TODO Auto-generated constructor stub
	}

	public LancamentoEstatisticaDia(TipoLancamento tipo, LocalDate dia, BigDecimal total) {
		this.tipo = tipo;
		this.dia = dia;
		this.total = total;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDia() {
		return dia;
	}

	public void setDia(LocalDate dia) {
		this.dia = dia;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
