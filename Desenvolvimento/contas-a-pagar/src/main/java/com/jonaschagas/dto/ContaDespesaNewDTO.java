package com.jonaschagas.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContaDespesaNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String nome;
	
	@Min(value = 1)
	private double valorOriginal;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;
	
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public ContaDespesaNewDTO() {
		super();
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

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	
}
