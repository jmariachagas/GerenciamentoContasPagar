package com.jonaschagas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class AtrasoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Min(value = 1)
	private int diasAtraso;

	@NotNull
	private Integer regraCalculo;

	@NotNull
	@OneToOne
	@JoinColumn(name = "conta_id")
	private ContaDespesa conta;

	public AtrasoPagamento(int diasAtraso, ContaDespesa conta, RegraCalculoAtraso regra) {
		super();
		this.diasAtraso = diasAtraso;
		this.conta = conta;
		this.regraCalculo = regra.getTipo();
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

	public ContaDespesa getConta() {
		return conta;
	}

	public void setConta(ContaDespesa conta) {
		this.conta = conta;
	}	

	public RegraCalculoAtraso getTipoCalculo() {
		return RegraCalculoAtraso.toEnum(regraCalculo);
	}	

	public void setTipoCalculo(RegraCalculoAtraso tipo) {
		this.regraCalculo = tipo.getTipo();
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
