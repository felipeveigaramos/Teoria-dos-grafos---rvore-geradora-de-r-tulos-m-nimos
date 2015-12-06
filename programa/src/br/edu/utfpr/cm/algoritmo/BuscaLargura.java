package br.edu.utfpr.cm.algoritmo;

import br.edu.utfpr.cm.algoritmo.entidades.Cor;
import br.edu.utfpr.cm.algoritmo.entidades.CorVertice;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.Aresta;
import br.edu.utfpr.cm.grafo.Grafo;

public class BuscaLargura implements Algoritmo {
    private Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g;
    private VerticeBuscaLargura s;

    public BuscaLargura(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g,
            VerticeBuscaLargura verticeInicial) {
        if (g.getVertice(verticeInicial.getId()) == null)
            throw new RuntimeException("O vértice de índice " + verticeInicial.getId() + " não pertence ao grafo "
                    + g.toString() + ". " + "Utilize um vértice válido como argumento do construtor da classe "
                    + this.getClass().getName());
        else {
            this.g = g;
            this.s = verticeInicial;
        }
    }

    // Algoritmo para inicialização do grafo na BFS
    public void inicializaGrafo() {
        VerticeBuscaLargura u;
        while (this.g.getVertices().hasNext()) {
            u = this.g.getVertices().next();
            if (u != this.s) {
                u.setCor(new CorVertice(Cor.Branco));
                u.setDistancia((int) Float.POSITIVE_INFINITY);
                u.setPai(null);
            }
        }
        s.setDistancia(0);
        s.setPai(null);
        s.setCor(new CorVertice(Cor.Cinza));
    }

    // TODO Exercicio 3.1 - Implementar a busca em largura
    @Override
    public void executar() {
    }

    public void imprimeGrafo(Grafo<VerticeBuscaLargura, Aresta<VerticeBuscaLargura, VerticeBuscaLargura>> g,
            VerticeBuscaLargura s, VerticeBuscaLargura v) {
        if (v.equals(s)) {
            System.out.print(s);
        } else {
            if (v.getPai() == null) {
                System.out.print("não há caminho entre " + s + " e " + v);
            } else {
                imprimeGrafo(g, s, v.getPai());
                System.out.print(v);
            }
        }
    }
}
