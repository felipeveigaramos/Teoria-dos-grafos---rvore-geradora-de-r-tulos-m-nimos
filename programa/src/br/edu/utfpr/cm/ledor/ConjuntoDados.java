/**
 * 
 */
package br.edu.utfpr.cm.ledor;

import java.util.ArrayList;

import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;
import br.edu.utfpr.cm.grafo.Vertice;

/**
 * @author Felipe Veiga Ramos
 *
 */
public class ConjuntoDados {
    private int numeroDeVertices;
    private int numeroDeRotulos;
    private ArrayList<ArrayList<String>> matriz;
    private Grafo<Vertice, Aresta<Vertice,Vertice>> grafo;
    /**
     * @param numeroDeVertices
     * @param numeroDeRotulos
     */
    public ConjuntoDados(int numeroDeVertices, int numeroDeRotulos) {
        super();
        this.numeroDeVertices = numeroDeVertices;
        this.numeroDeRotulos = numeroDeRotulos;
    }
    /**
     * @return the numeroDeVertices
     */
    public int getNumeroDeVertices() {
        return numeroDeVertices;
    }
    /**
     * @param numeroDeVertices the numeroDeVertices to set
     */
    public void setNumeroDeVertices(int numeroDeVertices) {
        this.numeroDeVertices = numeroDeVertices;
    }
    /**
     * @return the numeroDeRotulos
     */
    public int getNumeroDeRotulos() {
        return numeroDeRotulos;
    }
    /**
     * @param numeroDeRotulos the numeroDeRotulos to set
     */
    public void setNumeroDeRotulos(int numeroDeRotulos) {
        this.numeroDeRotulos = numeroDeRotulos;
    }
    /**
     * @return the matriz
     */
    public ArrayList<ArrayList<String>> getMatriz() {
        return matriz;
    }
    /**
     * @param matriz the matriz to set
     */
    public void setMatriz(ArrayList<ArrayList<String>> matriz) {
        this.matriz = matriz;
    }
    /**
     * @return the grafo
     */
    public Grafo<Vertice, Aresta<Vertice, Vertice>> getGrafo() {
        return grafo;
    }
    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(Grafo<Vertice, Aresta<Vertice, Vertice>> grafo) {
        this.grafo = grafo;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matriz == null) ? 0 : matriz.hashCode());
        result = prime * result + numeroDeRotulos;
        result = prime * result + numeroDeVertices;
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ConjuntoDados)) {
            return false;
        }
        ConjuntoDados other = (ConjuntoDados) obj;
        if (matriz == null) {
            if (other.matriz != null) {
                return false;
            }
        } else if (!matriz.equals(other.matriz)) {
            return false;
        }
        if (numeroDeRotulos != other.numeroDeRotulos) {
            return false;
        }
        if (numeroDeVertices != other.numeroDeVertices) {
            return false;
        }
        return true;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConjuntoDados [matriz=");
        builder.append(matriz);
        builder.append("]");
        return builder.toString();
    }
    
    
	}
