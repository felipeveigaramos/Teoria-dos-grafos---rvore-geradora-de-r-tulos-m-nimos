package br.edu.utfpr.cm.ledor;

import java.util.ArrayList;
import java.util.Iterator;

import br.edu.utfpr.cm.algoritmo.ContaComponentesConexas;
import br.edu.utfpr.cm.algoritmo.Jpso;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;
import br.edu.utfpr.cm.grafo.Vertice;

public class TesteLedorDados {

    public static void main(String[] args) {
        LedorDados ld = LedorDados.getInstance("instancias");
        ContaComponentesConexas ccc = new ContaComponentesConexas();
        ConjuntoDados cd;
        ArrayList<Double> rotulos = new ArrayList<Double>();
        Jpso jpso = new Jpso();
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> gr;
        
        
        for (int i = 0; i < 20; i++) {
            System.out.println("grafo: " + i);
            cd = ld.next();
            ccc.setGrafo(cd.getGrafo());
            ccc.executar();
            System.out.println("ccc: " + ccc.getQuantidadeDeComponentesConexas());
            jpso.setConjuntoDados(cd);
            jpso.executar();
            System.out.println(jpso.getRotulosResultantes());
            System.out.println(jpso.getTempoExecucao());
        }
    }

    private static int contaArestas(GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> gr) {

        int quantidade = 0;
        Iterator<ArestaPonderada<Vertice, Vertice>> arestas = gr.getArestas();
        ArestaPonderada<Vertice, Vertice> aresta;
        while (arestas.hasNext()) {
            quantidade++;
            aresta = arestas.next();
            System.out.println(aresta);
        }
        return quantidade;
    }

    private static int contaVertices(GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> gr) {
        int quantidade = 0;
        Iterator<Vertice> vertices = gr.getVertices();
        while (vertices.hasNext()) {
            quantidade++;
            vertices.next();
        }
        return quantidade;
    }
}