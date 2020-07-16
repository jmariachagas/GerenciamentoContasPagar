package com.jonaschagas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ContaDespesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private double valorOriginal;
	private Date dataVencimento;
	private Date dataPagamento;

	@OneToOne
	@JoinColumn(name = "atraso_id")
	// @MapsId
	private AtrasoPagamento atraso;

	public ContaDespesa() {
		super();
	}

	public ContaDespesa(String nome, double valorOriginal, Date dataVencimento, Date dataPagamento) {
		super();
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public long getId() {
		return id;
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

	public AtrasoPagamento getAtraso() {
		return atraso;
	}

	public void setAtraso(AtrasoPagamento atraso) {
		this.atraso = atraso;
	}
}
