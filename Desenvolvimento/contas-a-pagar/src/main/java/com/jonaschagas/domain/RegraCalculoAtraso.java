package com.jonaschagas.domain;

public enum RegraCalculoAtraso {

	CALCULO_1(1, 2, 0.1), 
	CALCULO_2(2, 3, 0.2), 
	CALCULO_3(3, 5, 0.3);

	private Integer tipo;
	private Integer multa;
	private double juros;

	private RegraCalculoAtraso(Integer tipo, Integer multa, double juros) {
		this.tipo = tipo;
		this.multa = multa;
		this.juros = juros;
	}

	public Integer getMulta() {
		return multa;
	}

	public void setMulta(Integer multa) {
		this.multa = multa;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}
	
	public static RegraCalculoAtraso toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}		
		for(RegraCalculoAtraso t : RegraCalculoAtraso.values()) {
			if(cod.equals(t.getTipo())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Código invalido");
	}
	
	public static RegraCalculoAtraso verificaRegra(int dias) {
		
		if(dias < 4) {
			return RegraCalculoAtraso.CALCULO_1;
		} else if(dias >= 4 && dias < 6) {
			return RegraCalculoAtraso.CALCULO_2;
		} else {
			return RegraCalculoAtraso.CALCULO_3;
		}		
	}
}
