/**
 * 
 */
package br.edu.utfpr.cm.algoritmo;

import java.util.Iterator;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.CorVertice;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;

/**
 * @author felipevr
 *
 */
public class ContaComponentesConexas implements Algoritmo {
    private Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo;
    private int quantidadeDeComponentesConexas;
    private int tempo;

    /**
     * 
     */
    public ContaComponentesConexas() {
        super();
    }

    /**
     * @param grafo
     */
    public ContaComponentesConexas(
            Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo) {
        super();
        this.grafo = grafo;
    }

    /**
     * @return the grafo
     */
    public Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo
     *            the grafo to set
     */
    public void setGrafo(
            Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> grafo) {
        this.grafo = grafo;
        this.quantidadeDeComponentesConexas = 0;
    }

    /**
     * @return the quantidadeDeComponentesConexas
     */
    public int getQuantidadeDeComponentesConexas() {
        return quantidadeDeComponentesConexas;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.edu.utfpr.cm.algoritmo.Algoritmo#executar()
     */
    @Override
    public void executar() {
        this.quantidadeDeComponentesConexas = 0;
        this.tempo = 0;
        Iterator<VerticeBuscaProfundidade> vertices = grafo.getVertices();
        VerticeBuscaProfundidade v;
        while (vertices.hasNext()) {
            v = vertices.next();
            v.setCor(new CorVertice(Cor.Branco));
            v.setPai(null);
            v.setTempoDescoberta((int) Double.POSITIVE_INFINITY);
            v.setTempoFinalizacao((int) Double.POSITIVE_INFINITY);
        }

        vertices = grafo.getVertices();
        while (vertices.hasNext()) {
            v = vertices.next();
            if (v.getCor().getCor() == Cor.Branco) {
                this.quantidadeDeComponentesConexas++;
                buscaProfundidade(v);

            }
        }

    }

    private void buscaProfundidade(VerticeBuscaProfundidade v) {
        VerticeBuscaProfundidade v2;
        v.setTempoDescoberta(++tempo);
        v.setCor(new CorVertice(Cor.Cinza));
        Iterator<VerticeBuscaProfundidade> verticesAdjacentes = grafo
                .getVerticesAdjacentes(v);
        while (verticesAdjacentes.hasNext()) {
            v2 = verticesAdjacentes.next();
            v2.setPai(v);
            buscaProfundidade(v2);
        }
        v.setCor(new CorVertice(Cor.Preto));
        v.setTempoFinalizacao(++tempo);
    }

}
