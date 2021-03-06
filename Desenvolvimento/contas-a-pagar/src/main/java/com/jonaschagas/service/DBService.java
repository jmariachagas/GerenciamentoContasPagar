package com.jonaschagas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jonaschagas.domain.AtrasoPagamento;
import com.jonaschagas.domain.ContaDespesa;
import com.jonaschagas.domain.RegraCalculoAtraso;
import com.jonaschagas.repository.AtrasoPagamentoRepository;
import com.jonaschagas.repository.ContaDespesaRepository;

@Service
public class DBService {
	
	@Autowired
	AtrasoPagamentoRepository atrasoPagamentoRepository;
	
	@Autowired
	ContaDespesaRepository contaDespesaRepository;
	
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	
	public void instantiateTestDatabase() throws ParseException {
		
		ContaDespesa conta1 = new ContaDespesa("conta de luz", 60.00, formato.parse("23/08/2020"), formato.parse("30/08/2020"));
		ContaDespesa conta2 = new ContaDespesa("conta de agua", 100.00, formato.parse("20/04/2020"), formato.parse("15/04/2020"));
										
		AtrasoPagamento atraso = new AtrasoPagamento(7, conta1, RegraCalculoAtraso.CALCULO_3);
		
		contaDespesaRepository.saveAll(Arrays.asList(conta1, conta2));	
		atrasoPagamentoRepository.saveAll(Arrays.asList(atraso));
		
	}

	
}
