package com.jonaschagas.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AtrasoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int diasAtraso;
	private int multa;
	private double juros;

	public AtrasoPagamento(int diasAtraso, int multa, double juros) {
		super();
		this.diasAtraso = diasAtraso;
		this.multa = multa;
		this.juros = juros;
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
}
