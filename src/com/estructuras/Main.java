package com.estructuras;

public class Main {

	public static void main(String[] args) {
		GrafoD grafo = new GrafoD("grafo1.in");
		grafo.dijkstra(1);

		
		//GrafoND grafo2 = new GrafoND("Grafo2.in");//new GrafoND("Grafo2.in");
		//System.out.println(grafo2.prim(1).toString());

		
		//GrafoND grafo = new GrafoND("grafo3.in");
		//grafo.floyd();

	}

}