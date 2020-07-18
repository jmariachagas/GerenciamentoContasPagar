package com.jonaschagas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonaschagas.dto.ContaDespesaDTO;
import com.jonaschagas.service.ContaDespesaService;

@RestController
@RequestMapping("/despesas")
public class ContaDespesaController {
	
	@Autowired
	ContaDespesaService contaDespesaService;
	
	@GetMapping
	public List<ContaDespesaDTO> buscar(){
		return contaDespesaService.listar();
	} 

}
