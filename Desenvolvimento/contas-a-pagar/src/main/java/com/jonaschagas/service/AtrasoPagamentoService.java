package com.jonaschagas.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonaschagas.domain.AtrasoPagamento;
import com.jonaschagas.domain.ContaDespesa;
import com.jonaschagas.domain.RegraCalculoAtraso;
import com.jonaschagas.repository.AtrasoPagamentoRepository;

@Service
public class AtrasoPagamentoService {

	@Autowired
	AtrasoPagamentoRepository atrasoPagamentoRepository;

	public AtrasoPagamento constroiRegraAtraso(ContaDespesa conta) {
		
		AtrasoPagamento atraso = new AtrasoPagamento();
		atraso.setDiasAtraso(calculaDiferencaDias(conta.getDataVencimento(), conta.getDataPagamento()));
		atraso.setTipoCalculo(RegraCalculoAtraso.verificaRegra(atraso.getDiasAtraso()));		
		return atraso;
	}

	protected int calculaDiferencaDias(Date vencimento, Date pagamento) {
		
		int dias = 0;
		int dias_meses = 0;
		int dias_anos = 0;
		LocalDate dataVencimento = transformaLocalDate(vencimento);
		LocalDate dataPagamento = transformaLocalDate(pagamento);		
		Period periodo = Period.between(dataVencimento, dataPagamento);		
		
		dias = periodo.getDays();
		dias_meses = periodo.getMonths();
		dias_anos = periodo.getYears();
		
		if(dias_meses > 0) {
			dias_meses = dias_meses * 30;
		}
		if(dias_anos > 0) {
			dias_anos = dias_anos * 365;
		}		
		return (dias + dias_meses + dias_anos);
	}

	public LocalDate transformaLocalDate(Date data) {
		LocalDate localDate = LocalDate.of(2020, 01, 01);
		data.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		localDate = data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}

}
