package com.estructuras;

public class Arista {
	private int nodoOrigen;
	private int nodoDestino;
	private int costo;
	
	
	public int getNodoOrigen() {
		return nodoOrigen;
	}
	public void setNodoOrigen(int nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}
	public int getNodoDestino() {
		return nodoDestino;
	}
	public void setNodoDestino(int nodoDestino) {
		this.nodoDestino = nodoDestino;
	}
	public int getCosto() {
		return costo;
	}
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public Arista(int nodoOrigen, int nodoDestino, int costo) {
		this.nodoOrigen = nodoOrigen;
		this.nodoDestino = nodoDestino;
		this.costo = costo;
	}
	
	
}
