package com.jonaschagas.repository;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jonaschagas.domain.ContaDespesa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContaDespesaRepositoryTest {

	Date data1;
	Date data2;

	@Before
	public void criaDatas() {
		new Date();
		data1 = Date.from(Instant.now());
		new Date();
		data2 = Date.from(Instant.now());
	}

	@Autowired
	private ContaDespesaRepository contaDespesaRepository;

	@Test
	public void readShouldFindNotNull() {
		ContaDespesa conta = new ContaDespesa("Agua", 100.00, data1, data2);
		ContaDespesa conta2 = new ContaDespesa("Luz", 200.00, data1, data2);
		this.contaDespesaRepository.saveAll(Arrays.asList(conta, conta2));
		
		List<ContaDespesa> list = this.contaDespesaRepository.findAll();
		
		Assertions.assertThat(list).isNotNull();
	}
	
	@Test
	public void readShouldFindAllData() {
		
		ContaDespesa conta = new ContaDespesa("Agua", 100.00, data1, data2);
		ContaDespesa conta2 = new ContaDespesa("Luz", 200.00, data1, data2);
		this.contaDespesaRepository.saveAll(Arrays.asList(conta, conta2));
		
		List<ContaDespesa> list = this.contaDespesaRepository.findAll();
		
		Assertions.assertThat(list).isEqualTo(Arrays.asList(conta, conta2));
	}
	
	@Test
	public void createShouldPersistData() {
		
		ContaDespesa conta = new ContaDespesa("Agua", 100.00, data1, data2);
		conta = this.contaDespesaRepository.save(conta);
		
		Assertions.assertThat(conta.getId()).isNotNull();
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void createShouldExceptionFieldName() {		
		ContaDespesa conta = new ContaDespesa("", 100.00, data1, data2);
		conta = this.contaDespesaRepository.save(conta);
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void createShouldExptionFieldValor() {		
		ContaDespesa conta = new ContaDespesa("Luz", 0.99, data1, data2);
		conta = this.contaDespesaRepository.save(conta);
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void createShouldExptionFieldData1() {
		
		ContaDespesa conta = new ContaDespesa("Luz", 100.00, null, data2);
		conta = this.contaDespesaRepository.save(conta);
	}
	
	@Test (expected = ConstraintViolationException.class)
	public void createShouldExptionFieldData2() {
		
		ContaDespesa conta = new ContaDespesa("Luz", 100.00, data1, null);
		conta = this.contaDespesaRepository.save(conta);
	}
}
