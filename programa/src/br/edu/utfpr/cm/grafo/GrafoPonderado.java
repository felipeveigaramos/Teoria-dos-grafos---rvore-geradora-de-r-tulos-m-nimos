package br.edu.utfpr.cm.grafo;

public interface GrafoPonderado<V extends Vertice, A extends ArestaPonderada<V, V>> extends Grafo<V, A> {
    public A getAresta(V u, V v);
}