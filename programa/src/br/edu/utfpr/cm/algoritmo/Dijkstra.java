package br.edu.utfpr.cm.algoritmo;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.PriorityQueue;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;

public class Dijkstra implements Algoritmo {

    private GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo;
    private VerticeBuscaLargura verticeInicial;
    private long tempoFinalizacao;

    public Dijkstra(
            GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo,
            VerticeBuscaLargura verticeInicial) {
        super();
        this.grafo = grafo;
        this.verticeInicial = verticeInicial;
    }

    /**
     * @return the grafo
     */
    public GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo
     *            the grafo to set
     */
    public void setGrafo(
            GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo) {
        this.grafo = grafo;
    }

    /**
     * @return the verticeInicial
     */
    public VerticeBuscaLargura getVerticeInicial() {
        return verticeInicial;
    }

    /**
     * @param verticeInicial
     *            the verticeInicial to set
     */
    public void setVerticeInicial(VerticeBuscaLargura verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    /**
     * @return the tempoFinalizacao
     */
    public long getTempoFinalizacao() {
        return tempoFinalizacao;
    }

    @Override
    public void executar() throws IllegalArgumentException {
        long tempoInicial = new GregorianCalendar().getTimeInMillis();
        PriorityQueue<VerticeBuscaLargura> fila = initializeSingleSource();
        ArrayList<VerticeBuscaLargura> vertices = new ArrayList<VerticeBuscaLargura>();

        VerticeBuscaLargura v1, v2;
        do {
            v1 = fila.poll();
            vertices.add(v1);
            Iterator<VerticeBuscaLargura> verticesAdjacentes = grafo.getVerticesAdjacentes(v1);
            if (verticesAdjacentes != null) {
                while (verticesAdjacentes.hasNext()) {
                    v2 = verticesAdjacentes.next();
                    relaxaAresta(v1, v2);
                }
            }
        } while (!fila.isEmpty());
        this.tempoFinalizacao = new GregorianCalendar().getTimeInMillis() - tempoInicial;
    }

    private void relaxaAresta(VerticeBuscaLargura v1, VerticeBuscaLargura v2) {
        VerticeBuscaLargura u = grafo.getVertice(v1.getId());
        VerticeBuscaLargura v = grafo.getVertice(v2.getId());
        ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura> aresta = grafo.getAresta(u, v);
        if (aresta == null) {
            throw new IllegalArgumentException("Aresta nulas: " + v1.getId() + ", " + v2.getId());
        }
        if (v.getDistancia() > (u.getDistancia() + aresta.getPeso())) {
            v.setDistancia(((int) u.getDistancia() + (int) aresta.getPeso()));
            v.setPai(u);
        }
    }

    private PriorityQueue<VerticeBuscaLargura> initializeSingleSource() {
        PriorityQueue<VerticeBuscaLargura> fila = new PriorityQueue<VerticeBuscaLargura>();
        VerticeBuscaLargura v;
        Iterator<VerticeBuscaLargura> vertices = grafo.getVertices();
        while (vertices.hasNext()) {
            v = vertices.next();
            v.setDistancia((int) Double.POSITIVE_INFINITY);
            v.setPai(null);
            fila.add(v);
        }
        this.verticeInicial.setDistancia(0);

        return fila;
    }
}
