package br.edu.utfpr.cm.grafo;

public class ArestaPonderada<U extends Vertice, V extends Vertice> extends
        Aresta<U, V> {
    double peso = Double.POSITIVE_INFINITY;

    public ArestaPonderada(U v1, V v2, double peso) {
        super(v1, v2);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
