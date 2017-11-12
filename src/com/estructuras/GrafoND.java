package com.estructuras;

import java.util.PriorityQueue;

public class GrafoND extends Grafo{

	
	
	public GrafoND(String ruta) {
		super(ruta);
	}

	@Override
	public void insertarArista(int i, int j, int costo) {
		this.getMatAdyacencia()[i][j] = costo;
		this.getMatAdyacencia()[j][i] = costo;	
	}
	
	public GrafoND prim() {
		
		
		
		
		
		return null;
	}
}
