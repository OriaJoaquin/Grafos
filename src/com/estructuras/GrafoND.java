package com.estructuras;

import java.util.Iterator;
import java.util.PriorityQueue;

public class GrafoND extends Grafo {

	public GrafoND(String ruta) {
		super(ruta);
	}

	public GrafoND(int cantVertices) {
		super(cantVertices);
	}

	@Override
	public void insertarArista(int i, int j, int costo) {
		this.getMatAdyacencia()[i][j] = costo;
		this.getMatAdyacencia()[j][i] = costo;
	}

	public GrafoND prim(int fuente) {
		final int FINAL_ARRAY = this.getNumVertices() + 1;
		
		int cantVertices = this.getNumVertices();
		boolean[] visitado = new boolean[FINAL_ARRAY];

		GrafoND result = new GrafoND(FINAL_ARRAY);

		PriorityQueue<Arista> cola = new PriorityQueue<>(
				(Arista a1, Arista a2) -> Integer.compare(a1.getCosto(), a2.getCosto()));

		int cont = 0;

		while (cont < cantVertices) {
			for (Iterator<Arista> it = this.getAristasInicidentes(fuente).iterator(); it.hasNext();) {

				Arista arc = it.next();

				if (!visitado[arc.getNodoDestino()]) {
					cola.offer(arc);
				}
			}

			Arista arco = cola.poll();

			if (!visitado[arco.getNodoDestino()]) {
				visitado[arco.getNodoDestino()] = true;
				result.insertarArista(arco.getNodoOrigen(), arco.getNodoDestino(), arco.getCosto());

				cont++;
				fuente = arco.getNodoDestino();
			}
		}

		return result;
	}

	@Override
	public String toString() {
		final int FINAL_ARRAY = this.getNumVertices() + 1;
		
		String result = "Nodos: " + this.getNumVertices();
		int[][] mat = this.getMatAdyacencia();

		for (int i = 1; i < FINAL_ARRAY; i++) {
			for (int j = i+1; j < FINAL_ARRAY; j++) {
				if (mat[i][j] != 0) {
					result += ("\n" + i + " " + j + " " + mat[i][j]);
				}
			}
		}

		return result;
	}
}
