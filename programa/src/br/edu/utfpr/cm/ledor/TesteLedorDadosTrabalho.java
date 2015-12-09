package br.edu.utfpr.cm.ledor;

import java.util.Iterator;
import java.util.Scanner;

import br.edu.utfpr.cm.algoritmo.Dijkstra;
import br.edu.utfpr.cm.algoritmo.entidades.VerticeBuscaLargura;
import br.edu.utfpr.cm.grafo.ArestaPonderada;
import br.edu.utfpr.cm.grafo.GrafoPonderado;

public class TesteLedorDadosTrabalho {
    public static void main(String[] args) {
        System.out.println("Iniciando");
        GrafoPonderado<VerticeBuscaLargura, ArestaPonderada<VerticeBuscaLargura, VerticeBuscaLargura>> grafo = LedorDadosTrabalho
                .leGrafo("roadNet-PA.txt");
        Dijkstra dijkstra;

        Scanner input = new Scanner(System.in);
        VerticeBuscaLargura v1, v2;
        String v1s = "", v2s = "";
        while (!v1s.equals("-1") && !v2s.equals("-1")) {
            System.out.println("Digite o primeiro vértice (-1 fim)");
            v1s = input.next();
            System.out.println("Digite o segundo vértice (-1 fim)");
            v2s = input.next();
            if (v1s.equals("-1") || v2s.equals("-1")) {
                System.out.println("Fim.");
                break;
            }
            v1 = grafo.getVertice(v1s);
            v2 =grafo.getVertice(v2s);
            if (v1 == null || v2 == null) {
                System.out.println("Vértices nulos encontrados.");
            }
            System.out.println(v1+" "+v2+"\n"+grafo.getAresta(v1, v2));
            dijkstra = new Dijkstra(grafo, v1);
            try {
                dijkstra.executar();
                System.out.println(dijkstra.getTempoFinalizacao() + " ms");
                System.out.println("Distância: " + v2.getDistancia());
                imprimeCaminho(v1, v2);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        input.close();

        Iterator<VerticeBuscaLargura> vertices = grafo.getVertices();
        while (vertices.hasNext()) {
            System.out.println(vertices.next());
        }
    }

    private static void imprimeCaminho(VerticeBuscaLargura v1, VerticeBuscaLargura v2) {
        String s = "";
        while (!v2.equals(v1)) {
            s = v2.getId() + "->";
            v2 = v2.getPai();
        }
        System.out.println(s);
    }
}