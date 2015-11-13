package br.edu.utfpr.cm.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;
import br.edu.utfpr.cm.grafo.Vertice;

public class GrafoPonderadoListaAdjacencia implements
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> {
    HashMap<Vertice, HashMap<Vertice, Double>> grafo = new HashMap<Vertice, HashMap<Vertice, Double>>();

    @Override
    public Iterator<Vertice> getVerticesAdjacentes(Vertice u) {
        if (u != null && grafo.containsKey(u)) {
            return grafo.get(u).keySet().iterator();
        } else {
            return null;
        }
    }

    @Override
    public Iterator<Vertice> getVertices() {
        return this.grafo.keySet().iterator();
    }

    @Override
    public Iterator<ArestaPonderada<Vertice, Vertice>> getArestas() {
        /*
         * Set<ArestaPonderada<Vertice, Vertice>> i = new
         * HashSet<ArestaPonderada<Vertice, Vertice>>(); for (Entry<Vertice,
         * HashMap<Vertice, Double>> adj : grafo.entrySet()) { for (Vertice u :
         * adj.getValue()) { i.add(new ArestaPonderada<Vertice,
         * Vertice>(adj.getKey(), u)); } } return i.iterator();
         */

        ArrayList<ArestaPonderada<Vertice, Vertice>> arestas = new ArrayList<ArestaPonderada<Vertice, Vertice>>();

        for (Vertice u : grafo.keySet()) {
            HashMap<Vertice, Double> adj = grafo.get(u);
            for (Vertice v : adj.keySet()) {
                arestas.add(new ArestaPonderada<Vertice, Vertice>(u, v, grafo
                        .get(u).get(v)));
            }
        }
        return arestas.iterator();
    }

    @Override
    public Vertice getVertice(String idVertice) {
        for (Entry<Vertice, HashMap<Vertice, Double>> e : grafo.entrySet()) {
            if (e.getKey().getId().equals(idVertice)) {
                return e.getKey();
            } else {
                for (Vertice v : e.getValue().keySet()) {
                    if (v.getId().equals(idVertice)) {
                        return v;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void adicionaVertice(Vertice verticeNoGrafo,
            Vertice verticeAdicionado) {
        // verifica se verticeNoGrafo esta no grafo
        Vertice v = getVertice(verticeNoGrafo.getId());
        if (v == null)
            throw new RuntimeException("O vértice com identificador "
                    + verticeNoGrafo.getId()
                    + " precisa necessariamente estar no grafo.");
        // else -> vertice esta no grafo !
        else {
            // verifica se o vertice verticeNoGrafo já possui
            // outros vértices adjacentes
            HashMap<Vertice, Double> adj = this.grafo.get(v);
            if (adj == null) {
                adj = new HashMap<Vertice, Double>();
                adj.put(verticeAdicionado, Double.POSITIVE_INFINITY);
                this.grafo.put(v, adj);
            } else {
                adj.put(verticeAdicionado, Double.POSITIVE_INFINITY);
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
            this.grafo.put(verticeAdicionado, new HashMap<Vertice, Double>());
        }
        // se o vértice já está no grafo, troca a referência
        verticeAdicionado = v;
    }

    @Override
    public void adicionaAresta(
            ArestaPonderada<Vertice, Vertice> arestaAdicionada) {
        if (arestaAdicionada.getVertice1() == null
                || arestaAdicionada.getVertice2() == null)
            throw new RuntimeException(
                    "Não é possível adicionar uma aresta com vértice nulos no grafo");
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
                    HashMap<Vertice, Double> adjV2 = new HashMap<Vertice, Double>();
                    adjV2.put(v2, arestaAdicionada.getPeso());

                    this.grafo.put(v, adjV2);
                } else {
                    // vertice 2 nao esta no grafo !
                    v = arestaAdicionada.getVertice1();
                    v2 = arestaAdicionada.getVertice2();
                    HashMap<Vertice, Double> adjV2 = new HashMap<Vertice, Double>();
                    adjV2.put(v2, arestaAdicionada.getPeso());
                    // adiciona listas de adjacencia do vertice 2 e do vertice 1
                    this.grafo.put(v, adjV2);
                    this.grafo.put(v2, new HashMap<Vertice, Double>());
                }
                // se vertice 1 esta no grafo, adiciona novo elemento na lista
                // de adjacência
            } else {
                // vértice 2 está no grafo?
                Vertice v2 = getVertice(arestaAdicionada.getVertice2().getId());
                if (v2 == null) {
                    v2 = arestaAdicionada.getVertice2();
                    // adiciona vertice 2 ao grafo
                    this.grafo.put(v2, new HashMap<Vertice, Double>());
                }

                // adiciona vertice 2 a lista de adjacencia do vertice 1
                HashMap<Vertice, Double> map = this.grafo.get(v);
                map.put(v2, arestaAdicionada.getPeso());
            }
        }
    }
}
