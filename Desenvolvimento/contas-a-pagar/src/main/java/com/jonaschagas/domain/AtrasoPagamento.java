package com.jonaschagas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AtrasoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int diasAtraso;
	private int multa;
	private double juros;
	
	@OneToOne
	@JoinColumn(name = "conta_id")
	private ContaDespesa conta;
	

	public AtrasoPagamento(int diasAtraso, int multa, double juros, ContaDespesa conta) {
		super();
		this.diasAtraso = diasAtraso;
		this.multa = multa;
		this.juros = juros;
		this.conta = conta;
	}

	public AtrasoPagamento() {
		super();
	}

	public long getId() {
		return id;
	}

	public int getDiasAtraso() {
		return diasAtraso;
	}

	public void setDiasAtraso(int diasAtraso) {
		this.diasAtraso = diasAtraso;
	}

	public int getMulta() {
		return multa;
	}

	public void setMulta(int multa) {
		this.multa = multa;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}	

	public ContaDespesa getConta() {
		return conta;
	}

	public void setConta(ContaDespesa conta) {
		this.conta = conta;
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
		AtrasoPagamento other = (AtrasoPagamento) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}
