package com.jonaschagas.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ContaDespesa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private double valorOriginal;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;

	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaDespesa other = (ContaDespesa) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
