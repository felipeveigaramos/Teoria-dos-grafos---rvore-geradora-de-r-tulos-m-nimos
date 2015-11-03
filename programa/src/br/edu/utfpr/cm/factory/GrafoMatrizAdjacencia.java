package br.edu.utfpr.cm.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;

//TODO Exercício 2.1 - Implementar os métodos para o grafo representado
//por matriz de adjacências

public class GrafoMatrizAdjacencia implements
        Grafo<Vertice, Aresta<Vertice, Vertice>> {
    private ArrayList<ArrayList<Integer>> matrizAdjacencia;
    private HashMap<Integer, Vertice> mapVertices;
    Integer ultimoAdicionado;

    /**
     * 
     */
    public GrafoMatrizAdjacencia() {
        super();
        this.matrizAdjacencia = new ArrayList<ArrayList<Integer>>();
        this.mapVertices = new HashMap<Integer, Vertice>();
        this.ultimoAdicionado = 0;
    }

    private Integer getNumVertice(Vertice u) {
        if (u == null) {
            return null;
        }
        for (Integer k : this.mapVertices.keySet()) {
            if (this.mapVertices.get(k).equals(u)) {
                return k;
            }
        }
        return null;
    }

    @Override
    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        Integer posicao = getNumVertice(u);
        if (u == null || posicao == null) {
            return null;
        }

        ArrayList<Vertice> verticesAdjacentes = new ArrayList<Vertice>();
        for (Integer i : this.matrizAdjacencia.get(posicao)) {
            if (this.matrizAdjacencia.get(posicao).get(i) != 0) {
                verticesAdjacentes.add(this.mapVertices.get(i));
            }
        }
        return verticesAdjacentes.iterator();
    }

    @Override
    public Iterator<Vertice> getVertices() {
        return this.mapVertices.values().iterator();
    }

    @Override
    public Iterator<Aresta<Vertice, Vertice>> getArestas() {
        ArrayList<Aresta<Vertice, Vertice>> arestas = new ArrayList<Aresta<Vertice, Vertice>>();
        for (Integer i = 0; i < this.matrizAdjacencia.size(); i++) {
            for (Integer j = 0; j < this.matrizAdjacencia.get(i).size(); j++) {
                if (this.matrizAdjacencia.get(i).get(j) != 0) {
                    arestas.add(new Aresta<Vertice, Vertice>(this.mapVertices
                            .get(i), this.mapVertices.get(j)));
                }
            }
        }
        return arestas.iterator();
    }

    @Override
    public Vertice getVertice(String idVertice) {
        for (Vertice v : this.mapVertices.values()) {
            if (v.getId().equals(idVertice)) {
                return v;
            }
        }
        return null;

    }

    @Override
    public void adicionaVertice(Vertice verticeNoGrafo,
            Vertice verticeAdicionado) {
        Integer posV1 = this.getNumVertice(verticeNoGrafo);
        Integer posV2 = this.getNumVertice(verticeAdicionado);
        if (verticeNoGrafo == null || verticeAdicionado == null
                || posV1 == null || posV2 != null) {
            return;
        }

        adicionaVertice(verticeAdicionado);

        posV2 = this.getNumVertice(verticeAdicionado);

        this.matrizAdjacencia.get(posV1).set(posV2, 1);
        this.matrizAdjacencia.get(posV2).set(posV1, 1);
    }

    @Override
    public void adicionaVertice(Vertice verticeAdicionado) {
        if (this.getNumVertice(verticeAdicionado) != null) {
            return;
        }

        this.mapVertices.put(this.ultimoAdicionado, verticeAdicionado);
        this.matrizAdjacencia.add(new ArrayList<Integer>());

        for (int i = 0; i < this.ultimoAdicionado; i++) {
            this.matrizAdjacencia.get(i).add(0);
        }

        this.ultimoAdicionado++;

        for (int i = 0; i < this.ultimoAdicionado; i++) {
            this.matrizAdjacencia.get(this.ultimoAdicionado - 1).add(0);
        }

    }

    @Override
    public void adicionaAresta(Aresta<Vertice, Vertice> arestaAdicionada) {
        Vertice v1 = arestaAdicionada.getVertice1();
        Vertice v2 = arestaAdicionada.getVertice2();
        Integer posV1 = this.getNumVertice(v1);
        Integer posV2 = this.getNumVertice(v2);

        if (posV1 == null) {
            adicionaVertice(v1);
            posV1 = this.getNumVertice(v1);
        }
        if (posV2 == null) {
            adicionaVertice(v2);
            posV2 = this.getNumVertice(v2);
        }

        this.matrizAdjacencia.get(posV1).set(posV2, 1);
        this.matrizAdjacencia.get(posV2).set(posV2, 1);
    }
}