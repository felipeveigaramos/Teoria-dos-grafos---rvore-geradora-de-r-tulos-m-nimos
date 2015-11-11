package br.edu.utfpr.cm.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;
import br.edu.utfpr.cm.grafo.Vertice;

public class GrafoPonderadoListaAdjacencia implements
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> {
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
    public Iterator<ArestaPonderada<Vertice, Vertice>> getArestas() {
        Set<ArestaPonderada<Vertice, Vertice>> i = new HashSet<ArestaPonderada<Vertice, Vertice>>();
        for (Entry<Vertice, ArrayList<Vertice>> adj : grafo.entrySet()) {
            for (Vertice u : adj.getValue()) {
                i.add(new ArestaPonderada<Vertice, Vertice>(adj.getKey(), u));
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
    public void adicionaVertice(Vertice verticeNoGrafo,
            Vertice verticeAdicionado) {
        // verifica se verticeNoGrafo esta no grafo
        Vertice v = getVertice(verticeNoGrafo.getId());
        if (v == null)
            throw new RuntimeException("O v�rtice com identificado "
                    + verticeNoGrafo.getId()
                    + " precisa necessariamente estar no grafo.");
        // else -> vertice esta no grafo !
        else {
            // verifica se o vertice verticeNoGrafo j� possui
            // outros v�rtices adjacentes
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
        // o v�rtice j� est� no grafo ?
        // se n�o, adiciona o v�rtice sem pai
        Vertice v = getVertice(verticeAdicionado.getId());
        if (v == null) {
            this.grafo.put(verticeAdicionado, new ArrayList<Vertice>());
        }
        // se o v�rtice j� est� no grafo, troca a refer�ncia
        verticeAdicionado = v;
    }

    @Override
    public void adicionaAresta(
            ArestaPonderada<Vertice, Vertice> arestaAdicionada) {
        if (arestaAdicionada.getVertice1() == null
                || arestaAdicionada.getVertice2() == null)
            throw new RuntimeException(
                    "N�o � poss�vel adicionar uma aresta com v�rtice nulos no grafo");
        else {
            // vertice 1 da aresta j� existe no grafo ?
            // se n�o, cria nova entrada na lista de adjac�ncias
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
                // de adjac�ncia
            } else {
                // v�rtice 2 est� no grafo?
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
}
