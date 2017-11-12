package com.estructuras;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public abstract class Grafo {
	private int numVertices;
	private int numAristas;
	private int[][] matAdyacencia;

	public static final int MAXIMO_COSTO = 999;

	// public boolean existeArista(int i, int j);

	public Grafo(String ruta) {
		this.leerGrafo(ruta);
	}

	public Grafo(int cantVertices) {
		this.matAdyacencia = new int[cantVertices + 1][cantVertices + 1];
		this.numVertices = cantVertices;
	}

	public abstract void insertarArista(int i, int j, int costo);

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public int getNumAristas() {
		return numAristas;
	}

	public void setNumAristas(int numAristas) {
		this.numAristas = numAristas;
	}

	public int[][] getMatAdyacencia() {
		return matAdyacencia;
	}

	public int getPesoArista(int i, int j) {
		return this.matAdyacencia[i][j];
	}

	private Grafo leerGrafo(String ruta) {
		try {
			Scanner sc = new Scanner(new File(ruta));
			this.numVertices = sc.nextInt();
			this.numAristas = sc.nextInt();

			this.matAdyacencia = new int[this.numVertices + 1][this.numVertices + 1];

			int verticeOrigen, verticeDestino, costo;

			while (sc.hasNextLine()) {
				verticeOrigen = sc.nextInt();
				verticeDestino = sc.nextInt();
				costo = sc.nextInt();

				this.insertarArista(verticeOrigen, verticeDestino, costo);
			}

			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}

	public void dijkstra(int fuente) {
		int[] distancia = new int[this.numVertices + 1];
		int[] padre = new int[this.numVertices + 1];
		boolean[] visto = new boolean[this.numVertices + 1];
		int i;
		PriorityQueue<Integer> cola = new PriorityQueue<Integer>();

		for (i = 1; i < this.numVertices + 1; i++) {
			distancia[i] = MAXIMO_COSTO;
			padre[i] = 0;
			visto[i] = false;
		}

		distancia[fuente] = 0;

		cola.add(fuente);

		while (!cola.isEmpty()) {
			int vertice = cola.poll();

			visto[vertice] = true;

			for (int v : getAdyacentes(vertice)) {
				if (!visto[v] && distancia[v] > distancia[vertice] + this.getPesoArista(vertice, v)) {
					distancia[v] = distancia[vertice] + this.getPesoArista(vertice, v);
					padre[v] = vertice;
					cola.add(v);
				}
			}
		}

		int k;
		for (k = 1; k < this.numVertices + 1; k++) {
			System.out.println("Distancia a " + k + " es " + distancia[k]);
		}

		System.out.println("--------------------------------------------");

		for (k = 1; k < this.numVertices + 1; k++) {
			System.out.println("Padre de " + k + " es " + padre[k]);
		}

	}

	public LinkedList<Integer> getAdyacentes(int nodo) {
		int i;
		LinkedList<Integer> vecinos = new LinkedList<Integer>();

		for (i = 1; i < this.numVertices + 1; i++) {
			if (this.matAdyacencia[nodo][i] > 0)
				vecinos.add(i);
		}
		return vecinos;
	}

	public LinkedList<Arista> getAristasInicidentes(int v) {
		LinkedList<Arista> aristas = new LinkedList<Arista>();
		int j;

		for (j = 1; j < this.numVertices + 1; j++) {
			if (this.matAdyacencia[v][j] != 0) {
				aristas.add(new Arista(v, j, this.matAdyacencia[v][j]));
			}
		}

		return aristas;
	}
}
