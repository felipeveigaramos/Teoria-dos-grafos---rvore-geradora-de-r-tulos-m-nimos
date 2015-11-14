package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaProfundidade;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;

public class BuscaProfundidade implements Algoritmo {
    private Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g;
    private VerticeBuscaProfundidade s;

    public BuscaProfundidade(
            Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g,
            VerticeBuscaProfundidade verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null)
            throw new RuntimeException(
                    "O vértice de índice "
                            + verticeInicial.getId()
                            + " não pertence ao grafo "
                            + g.toString()
                            + ". "
                            + "Utilize um vértice válido como argumento do construtor da classe "
                            + this.getClass().getName());
        else {
            this.g = g;
            this.s = verticeInicial;
        }
    }

    // TODO Exercicio 4.1 - Implementar o algoritmo de inicializacao da busca em
    // profundidade
    public void inicializaGrafo() {

    }

    // TODO Exercicio 4.2 - Implementar a busca em profundidade
    @Override
    public void executar() {

    }

    // TODO Exercicio 4.3 - Implementar a impressao do grafo
    public void imprimeGrafo(
            Grafo<VerticeBuscaProfundidade, Aresta<VerticeBuscaProfundidade, VerticeBuscaProfundidade>> g,
            VerticeBuscaProfundidade s, VerticeBuscaProfundidade v) {

    }

}
