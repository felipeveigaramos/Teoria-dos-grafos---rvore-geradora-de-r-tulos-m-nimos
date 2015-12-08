package br.edu.utfpr.cm.ledor;

import br.edu.utfpr.cm.algoritmo.Dijkstra;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;

public class TesteLedorDadosTrabalho {
    public static void main(String[] args) {
        System.out.println("Iniciando");

        GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo = LedorDadosTrabalho
                .leGrafo("roadNet-PA.txt");

        Dijkstra dijkstra = new Dijkstra(grafo, grafo.getVertice("0"));
        dijkstra.executar();
        System.out.println(dijkstra.getTempoFinalizacao() + " ms");

        /*
         * Iterator<VerticeBuscaLargura> vertices = grafo.getVertices(); while
         * (vertices.hasNext()) { System.out.println(vertices.next()); }
         */
    }
}