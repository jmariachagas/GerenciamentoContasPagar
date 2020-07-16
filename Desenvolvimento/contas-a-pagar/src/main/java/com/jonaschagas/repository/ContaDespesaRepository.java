package com.jonaschagas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jonaschagas.domain.ContaDespesa;

@Repository
public interface ContaDespesaRepository extends JpaRepository<ContaDespesa, Long>{

}
