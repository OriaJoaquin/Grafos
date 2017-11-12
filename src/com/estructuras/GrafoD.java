package com.estructuras;

public class GrafoD extends Grafo {

	public GrafoD(String ruta) {
		super(ruta);
	}
	
	@Override
	public void insertarArista(int i, int j, int costo) {
		this.getMatAdyacencia()[i][j] = costo;
	}

}
