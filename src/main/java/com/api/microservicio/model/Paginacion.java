package com.api.microservicio.model;

public class Paginacion {
	private int numeroElementos;
	private int pagina;
	
	public int getNumeroElementos() {
		return numeroElementos;
	}
	public void setNumeroElementos(int numeroElementos) {
		this.numeroElementos = numeroElementos;
	}
	public int getPagina() {
		return pagina;
	}
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
}
