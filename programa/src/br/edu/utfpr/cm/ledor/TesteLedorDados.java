package br.edu.utfpr.cm.ledor;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        ConjuntoDados cd = null;
        ArrayList<Double> rotulos = new ArrayList<Double>();
        Jpso jpso = new Jpso();
        GrafoPonderado<Vertice, ArestaPonderada<Vertice, Vertice>> gr;
        int quantidadeDeRotulos;
        int quantidadeDeGrafos;
        long tempo;

        long tempoInicial = new GregorianCalendar().getTimeInMillis();
        int i = 0;
        do {
            quantidadeDeGrafos = 0;
            quantidadeDeRotulos = 0;
            tempo = 0L;
            System.out.print("Arquivo #: " + ++i + " ");
            for (int j = 0; j < 10; j++) {
                cd = ld.next();
                if (j == 0) {
                    System.out.println(cd.getNomedoArquivoDeOrigemDosDados() + " v: " + cd.getNumeroDeVertices() + " r: " + cd.getNumeroDeRotulos());
                }
                System.out.println("Grafo #: " + (j+1));

                ccc.setGrafo(cd.getGrafo());
                ccc.executar();
                if (ccc.getQuantidadeDeComponentesConexas() == 1) {
                    quantidadeDeGrafos++;
                    jpso.setConjuntoDados(cd);
                    jpso.executar();
                    System.out.println("Quantidade de rótulos: " + jpso.getRotulosResultantes().size());
                    quantidadeDeRotulos += jpso.getRotulosResultantes().size();
                    System.out.println("Tempo de execução : " + jpso.getTempoExecucao() + " ms");
                    tempo += jpso.getTempoExecucao();
                }
            }
            System.out.println("Número de instâncias deste arquivo: " + quantidadeDeGrafos);
            System.out.println(
                    "Quantidade média de rótulos por instância: " + (quantidadeDeRotulos / (double)quantidadeDeGrafos));
            System.out.println("Tempo de execução médio: " + (tempo / (double)quantidadeDeGrafos) + " ms");
            System.out.println();
        } while (cd != null);
        
        long tempoFinal = new GregorianCalendar().getTimeInMillis() - tempoInicial;
        System.out.println("Tempo total de execução, levando em conta parse dos arquivos: " + tempoFinal + " ms");
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