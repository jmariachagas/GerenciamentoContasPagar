package com.jonaschagas.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jonaschagas.domain.ContaDespesa;

public class ContaDespesaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private long id;
	
	private String nome;
	private double valorOriginal;
	private int diasAtraso;
	private Date dataPagamento;
	
	private double valorCorrigido;
	
	public ContaDespesaDTO (ContaDespesa conta, int diasAtraso, double valorCorrigido) {
		this.id = conta.getId();
		this.nome = conta.getNome();
		this.valorOriginal = conta.getValorOriginal();
		this.diasAtraso = diasAtraso;
		this.dataPagamento = conta.getDataPagamento();
		this.valorCorrigido = valorCorrigido;
	}
	
	public ContaDespesaDTO() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}
}
