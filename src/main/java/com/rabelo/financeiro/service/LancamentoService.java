package com.rabelo.financeiro.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.rabelo.financeiro.dto.LancamentoEstatisticaPessoa;
import com.rabelo.financeiro.model.Lancamento;
import com.rabelo.financeiro.model.Pessoa;
import com.rabelo.financeiro.repository.LancamentoRepository;
import com.rabelo.financeiro.repository.PessoaRepository;
import com.rabelo.financeiro.service.exception.PessoaInexistenteOuInativaException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public byte[] relatorioPorPessoa(LocalDate inicio, LocalDate fim)throws Exception {
		List<LancamentoEstatisticaPessoa> dados = lancamentoRepository.porPessoa(inicio, fim);
	Map<String, Object> parametros = new HashMap<>();
	parametros.put("DT_INICIO", Date.valueOf(inicio));
	parametros.put("DT_FIM", Date.valueOf(fim));
	parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
	
	InputStream inputStream = this.getClass()
			      .getResourceAsStream("/relatorios/Lancamentos-por-pessoa.jasper");
	JasperPrint jasperPrint = JasperFillManager
			               .fillReport(inputStream, parametros, 
			               new JRBeanCollectionDataSource(dados));
	
	return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public Lancamento salvar(Lancamento lancamento) {
		Optional<Pessoa> pessoaCodigo = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

		if (!pessoaCodigo.isPresent() || pessoaCodigo.get().isInativo()) {

			throw new PessoaInexistenteOuInativaException();

		}
		return lancamentoRepository.save(lancamento);
	}

}
