package com.estructuras;

import java.util.Iterator;
import java.util.PriorityQueue;

public class GrafoND extends Grafo {

	private static final int COMIENZO_ARRAY = 1;

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

	public void coloreoSecuencial() {
		final int FINAL_ARRAY = this.getNumVertices() + 1;
		int result[] = new int[FINAL_ARRAY];

		// Assign the first color to first vertex
		result[COMIENZO_ARRAY] = 1;

		// Initialize remaining V-1 vertices as unassigned
		for (int u = COMIENZO_ARRAY + 1; u < FINAL_ARRAY; u++)
			result[u] = -1; // no color is assigned to u

		// A temporary array to store the available colors. True
		// value of available[cr] would mean that the color cr is
		// assigned to one of its adjacent vertices
		boolean available[] = new boolean[FINAL_ARRAY];
		for (int cr = COMIENZO_ARRAY; cr < FINAL_ARRAY; cr++)
			available[cr] = false;

		// Assign colors to remaining V-1 vertices
		for (int u = COMIENZO_ARRAY+1; u < FINAL_ARRAY; u++) {
			// Process all adjacent vertices and flag their colors
			// as unavailable
			Iterator<Integer> it = this.getAdyacentes(u).iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = true;
			}

			// Find the first available color
			int cr;
			for (cr = COMIENZO_ARRAY; cr < FINAL_ARRAY; cr++)
				if (available[cr] == false)
					break;

			result[u] = cr; // Assign the found color

			// Reset the values back to false for the next iteration
			it = this.getAdyacentes(u).iterator();
			while (it.hasNext()) {
				int i = it.next();
				if (result[i] != -1)
					available[result[i]] = false;
			}
		}

		// print the result
		for (int u = COMIENZO_ARRAY; u < FINAL_ARRAY; u++)
			System.out.println("Vertex " + u + " --->  Color " + result[u]);
	}

	@Override
	public String toString() {
		final int FINAL_ARRAY = this.getNumVertices() + 1;

		String result = "Nodos: " + this.getNumVertices();
		int[][] mat = this.getMatAdyacencia();

		for (int i = 1; i < FINAL_ARRAY; i++) {
			for (int j = i + 1; j < FINAL_ARRAY; j++) {
				if (mat[i][j] != 0) {
					result += ("\n" + i + " " + j + " " + mat[i][j]);
				}
			}
		}

		return result;
	}
}
