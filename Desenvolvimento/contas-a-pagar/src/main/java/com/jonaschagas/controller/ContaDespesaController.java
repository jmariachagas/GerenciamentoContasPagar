package com.jonaschagas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jonaschagas.domain.ContaDespesa;
import com.jonaschagas.dto.ContaDespesaDTO;
import com.jonaschagas.dto.ContaDespesaNewDTO;
import com.jonaschagas.service.ContaDespesaService;

@RestController
@RequestMapping("/despesas")
public class ContaDespesaController {

	@Autowired
	ContaDespesaService contaDespesaService;

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody ContaDespesaNewDTO contaDespesaNewDTO) {
		ContaDespesa conta = contaDespesaService.fromDTO(contaDespesaNewDTO);
		conta = contaDespesaService.salvar(conta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping
	public List<ContaDespesaDTO> buscar() {
		return contaDespesaService.listar();
	}
}