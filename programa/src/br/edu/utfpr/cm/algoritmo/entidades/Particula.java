package br.edu.utfpr.cm.algoritmo.entidades;

import java.util.ArrayList;

import br.edu.utfpr.cm.factory.GrafoFactory;
import br.edu.utfpr.cm.factory.GrafoPonderadoListaAdjacencia;
import br.edu.utfpr.cm.factory.Representacao;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;
import br.edu.utfpr.cm.grafo.Vertice;

public class Particula {
    GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafo;
    private ArrayList<Double> rotulos;
    private ArrayList<Double> melhorPosicao;
    private int numeroDeRotulos;
    private int numeroDeRotulosNaMelhorPosicao;

    public Particula() {
        super();
        this.grafo = (GrafoPonderadoListaAdjacencia) GrafoFactory
                .constroiGrafo(Representacao.PONDERADO_LISTA_ADJACENCIA);
        this.rotulos = new ArrayList<Double>();
    }

    public Particula(GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafo, ArrayList<Double> rotulos) {
        super();
        this.grafo = grafo;
        this.rotulos = rotulos;
    }

    /**
     * @return the grafo
     */
    public GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo
     *            the grafo to set
     */
    public void setGrafo(GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> grafo) {
        this.grafo = grafo;
    }

    /**
     * @return the rotulos
     */
    public ArrayList<Double> getRotulos() {
        return rotulos;
    }

    /**
     * @param rotulos
     *            the rotulos to set
     */
    public void setRotulos(ArrayList<Double> rotulos) {
        this.rotulos = rotulos;
        this.numeroDeRotulos = rotulos.size();
    }

    /**
     * @return the melhorPosicao
     */
    public ArrayList<Double> getMelhorPosicao() {
        return melhorPosicao;
    }

    /**
     * @param melhorPosicao
     *            the melhorPosicao to set
     */
    public void setMelhorPosicao(ArrayList<Double> melhorPosicao) {
        this.melhorPosicao = melhorPosicao;
        this.numeroDeRotulosNaMelhorPosicao = melhorPosicao.size();
    }

    /**
     * @return the numeroDeRotulos
     */
    public int getNumeroDeRotulos() {
        return numeroDeRotulos;
    }

    /**
     * @return the numeroDeRotulosNaMelhorPosicao
     */
    public int getNumeroDeRotulosNaMelhorPosicao() {
        return numeroDeRotulosNaMelhorPosicao;
    }
}