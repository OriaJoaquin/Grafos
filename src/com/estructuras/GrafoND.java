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
		int cantVertices = this.getNumVertices();
		boolean[] visitado = new boolean[cantVertices + 1];

		GrafoND result = new GrafoND(cantVertices);

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

	private int buscarVerticeMenorPeso() { // Se puede simplificar por que la matriz es simetrica
		int v = 0;
		int costo = this.MAXIMO_COSTO;
		int cantVertices = this.getNumVertices();
		int[][] mat = this.getMatAdyacencia();
		int i, j;

		for (i = 1; i < cantVertices + 1; i++) {
			for (j = 1; j < cantVertices + 1; j++) {
				if (mat[i][j] < costo)
					v = i;
			}
		}

		return v;
	}

	@Override
	public String toString() {
		String result = "Nodos: " + this.getNumVertices();
		int i, j;
		int[][] mat = this.getMatAdyacencia();

		for (i = 1; i < this.getNumVertices() + 1; i++) {
			for (j = i+1; j < this.getNumVertices() + 1; j++) {
				if (mat[i][j] != 0) {

					result += ("\n" + i + " " + j + " " + mat[i][j]);
				}
			}
		}

		return result;
	}
}
