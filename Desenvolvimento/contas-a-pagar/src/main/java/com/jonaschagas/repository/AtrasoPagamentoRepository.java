package com.jonaschagas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonaschagas.domain.AtrasoPagamento;

@Repository
public interface AtrasoPagamentoRepository extends JpaRepository<AtrasoPagamento, Long> {

}
