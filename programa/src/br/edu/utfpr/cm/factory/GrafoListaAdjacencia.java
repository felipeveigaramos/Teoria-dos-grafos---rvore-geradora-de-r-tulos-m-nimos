package br.edu.utfpr.cm.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;

public class GrafoListaAdjacencia implements Grafo<Vertice, Aresta<Vertice, Vertice>> {
    HashMap<Vertice, ArrayList<Vertice>> grafo = new HashMap<Vertice, ArrayList<Vertice>>();

    @Override
    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        if (u != null && grafo.containsKey(u)) {
            return grafo.get(u).iterator();
        } else {
            return null;
        }

    }

    @Override
    public Iterator<Vertice> getVertices() {
        return this.grafo.keySet().iterator();
    }

    @Override
    public Iterator<Aresta<Vertice, Vertice>> getArestas() {
        Set<Aresta<Vertice, Vertice>> i = new HashSet<Aresta<Vertice, Vertice>>();
        for (Entry<Vertice, ArrayList<Vertice>> adj : grafo.entrySet()) {
            for (Vertice u : adj.getValue()) {
                i.add(new Aresta<Vertice, Vertice>(adj.getKey(), u));
            }
        }
        return i.iterator();
    }

    @Override
    public Vertice getVertice(String idVertice) {
        for (Entry<Vertice, ArrayList<Vertice>> e : grafo.entrySet()) {
            if (e.getKey().getId().equals(idVertice)) {
                return e.getKey();
            } else {
                ArrayList<Vertice> adj = e.getValue();
                for (Vertice v : adj)
                    if (v.getId().equals(idVertice))
                        return v;
            }
        }
        return null;
    }

    @Override
    public void adicionaVertice(Vertice verticeNoGrafo, Vertice verticeAdicionado) {
        // verifica se verticeNoGrafo esta no grafo
        Vertice v = getVertice(verticeNoGrafo.getId());
        if (v == null)
            throw new RuntimeException("O vértice com identificado " + verticeNoGrafo.getId()
                    + " precisa necessariamente estar no grafo.");
        // else -> vertice esta no grafo !
        else {
            // verifica se o vertice verticeNoGrafo já possui
            // outros vértices adjacentes
            ArrayList<Vertice> adj = this.grafo.get(v);
            if (adj == null) {
                adj = new ArrayList<Vertice>();
                adj.add(verticeAdicionado);
                this.grafo.put(v, adj);
            } else {
                adj.add(verticeAdicionado);
                this.grafo.put(v, adj);
            }
        }
    }

    @Override
    public void adicionaVertice(Vertice verticeAdicionado) {
        // o vértice já está no grafo ?
        // se não, adiciona o vértice sem pai
        Vertice v = getVertice(verticeAdicionado.getId());
        if (v == null) {
            this.grafo.put(verticeAdicionado, new ArrayList<Vertice>());
        }
        // se o vértice já está no grafo, troca a referência
        verticeAdicionado = v;
    }

    @Override
    public void adicionaAresta(Aresta<Vertice, Vertice> arestaAdicionada) {
        if (arestaAdicionada.getVertice1() == null || arestaAdicionada.getVertice2() == null)
            throw new RuntimeException("Não é possível adicionar uma aresta com vértice nulos no grafo");
        else {
            // vertice 1 da aresta já existe no grafo ?
            // se não, cria nova entrada na lista de adjacências
            Vertice v = getVertice(arestaAdicionada.getVertice1().getId());
            if (v == null) {
                // recupera vertice 2
                Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
                // vertice 2 ja esta no grafo ?
                if (v2 != null) {
                    v = arestaAdicionada.getVertice1();
                    ArrayList<Vertice> adjV2 = new ArrayList<Vertice>();
                    adjV2.add(v2);

                    this.grafo.put(v, adjV2);
                } else {
                    // vertice 2 nao esta no grafo !
                    v = arestaAdicionada.getVertice1();
                    v2 = arestaAdicionada.getVertice2();
                    ArrayList<Vertice> adjV2 = new ArrayList<Vertice>();
                    adjV2.add(v2);

                    // adiciona listas de adjacencia do vertice 2 e do vertice 1
                    this.grafo.put(v, adjV2);
                    this.grafo.put(v2, new ArrayList<Vertice>());
                }
                // se vertice 1 esta no grafo, adiciona novo elemento na lista
                // de adjacência
            } else {
                // vértice 2 está no grafo?
                Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
                if (v2 == null) {
                    v2 = arestaAdicionada.getVertice2();
                    // adiciona vertice 2 ao grafo
                    this.grafo.put(v2, new ArrayList<Vertice>());
                }

                // adiciona vertice 2 a lista de adjacencia do vertice 1
                List<Vertice> l = this.grafo.get(v);
                l.add(v2);
            }
        }
    }

    public void imprime() {
        System.out.println(grafo);
    }
}
