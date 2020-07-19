package com.jonaschagas.repository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jonaschagas.domain.AtrasoPagamento;
import com.jonaschagas.domain.ContaDespesa;
import com.jonaschagas.domain.RegraCalculoAtraso;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AtrasoPagamentoRepositoryTest {

	ContaDespesa conta;

	@Autowired
	AtrasoPagamentoRepository atrasoPagamentoRepository;

	@Autowired
	ContaDespesaRepository contaDespesaRepository;

	@Before
	public void criaDatas() {
		Date data1 = Date.from(Instant.now());
		Date data2 = Date.from(Instant.now());
		conta = new ContaDespesa("luz", 30.00, data1, data2);
		contaDespesaRepository.save(conta);
	}

	@Test
	public void createShouldPersistData() {

		AtrasoPagamento atraso = new AtrasoPagamento(10, conta, RegraCalculoAtraso.CALCULO_3);
		atrasoPagamentoRepository.save(atraso);
		Assertions.assertThat(atraso.getId()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void createShouldExceptionMinFieldDiasAtraso() {
		AtrasoPagamento atraso = new AtrasoPagamento(0, conta, RegraCalculoAtraso.CALCULO_3);
		atrasoPagamentoRepository.save(atraso);
	}

	@Test(expected = ConstraintViolationException.class)
	public void createShouldExceptionFieldConta() {
		AtrasoPagamento atraso = new AtrasoPagamento(10, null, RegraCalculoAtraso.CALCULO_3);
		atrasoPagamentoRepository.save(atraso);
	}
	
	@Test
	public void readShouldAtrasoPagamentoByIdContaDespesa() {
		
		AtrasoPagamento atraso = new AtrasoPagamento(5, conta, RegraCalculoAtraso.CALCULO_2);
		atrasoPagamentoRepository.save(atraso);
		
		Optional<AtrasoPagamento> atraso2 = atrasoPagamentoRepository.findByConta(conta);		
		Assertions.assertThat(atraso2.get().getId()).isNotNull();		
	}
}
