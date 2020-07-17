package com.jonaschagas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonaschagas.domain.AtrasoPagamento;
import com.jonaschagas.domain.ContaDespesa;

@Repository
public interface AtrasoPagamentoRepository extends JpaRepository<AtrasoPagamento, Long> {
	
	Optional<AtrasoPagamento> findByConta(ContaDespesa conta);

}
